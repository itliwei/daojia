<template>
    <div class="area-container">
        <el-col :span="24" class="toolbar">
            <el-form :inline="true" :model="areaQueryModel"  size="mini">
                <el-form-item :span="6" label="父ID">
                    <el-input v-model="areaQueryModel.pidEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="名称">
                    <el-input v-model="areaQueryModel.nameEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="类型">
                    <el-input v-model="areaQueryModel.mergerNameEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="类型">
                    <el-input v-model="areaQueryModel.codeEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="邮政编码">
                    <el-input v-model="areaQueryModel.zipCodeEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="类型">
                    <el-input v-model="areaQueryModel.firstEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="类型">
                    <el-input v-model="areaQueryModel.lngEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="类型">
                    <el-input v-model="areaQueryModel.latEQ" placeholder=""></el-input>
                </el-form-item>
            <el-form-item :span="6" >
                <el-button type="primary" v-on:click="getList" size="mini">查询</el-button>
            </el-form-item>
            </el-form>
        </el-col>
        <div>
            <el-button type="danger" @click="addVisible=true" size="mini">添加</el-button>
        </div>

        <el-table :data="tableData" highlight-current-row v-loading="listLoading" style="width: 100%;" size="mini">
            <el-table-column type="index" width="50"></el-table-column>
                <el-table-column prop="pid" label="父ID"></el-table-column>
                <el-table-column prop="shortName" label="简称"></el-table-column>
                <el-table-column prop="name" label="名称"></el-table-column>
                <el-table-column prop="mergerName" label="类型"></el-table-column>
                <el-table-column prop="code" label="类型"></el-table-column>
                <el-table-column prop="zipCode" label="邮政编码"></el-table-column>
                <el-table-column prop="first" label="类型"></el-table-column>
                <el-table-column prop="lng" label="类型"></el-table-column>
                <el-table-column prop="lat" label="类型"></el-table-column>
            <el-table-column
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="getInfo(scope.row.id)" type="text" size="mini">查看</el-button>
                    <el-button @click="updateDialog(scope.row)" type="text" size="mini">修改</el-button>
                    <el-button @click="delete(scope.row.id)" type="text" size="mini">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination  style="float:right" background
                        @current-change="handleCurrentChange"
                        layout="prev, pager, next"
                        :total="total">
        </el-pagination>

        <el-dialog title="添加" :visible.sync="addVisible" >
            <el-form :model="areaDTOAdd" label-width="80px" ref="addForm" :rules="addFormRules" size="mini">
                    <el-form-item label="父ID" prop="pid">
                            <el-input v-model="areaDTOAdd.pid" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="简称" prop="shortName">
                            <el-input v-model="areaDTOAdd.shortName" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="名称" prop="name">
                            <el-input v-model="areaDTOAdd.name" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="类型" prop="mergerName">
                            <el-input v-model="areaDTOAdd.mergerName" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="类型" prop="code">
                            <el-input v-model="areaDTOAdd.code" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="邮政编码" prop="zipCode">
                            <el-input v-model="areaDTOAdd.zipCode" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="类型" prop="first">
                            <el-input v-model="areaDTOAdd.first" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="类型" prop="lng">
                            <el-input v-model="areaDTOAdd.lng" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="类型" prop="lat">
                            <el-input v-model="areaDTOAdd.lat" placeholder=""></el-input>
                    </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="handleClose('addForm')" size="mini">取 消</el-button>
                <el-button type="primary" @click="handlerSubmit('addForm')" size="mini">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="修改" :visible.sync="updateVisible" >
            <el-form :model="areaDTOUpdate" label-width="80px" ref="updateForm" :rules="updateFormRules">
                    <el-form-item label="父ID" prop="pid">
                            <el-input v-model="areaDTOUpdate.pid" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="简称" prop="shortName">
                            <el-input v-model="areaDTOUpdate.shortName" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="名称" prop="name">
                            <el-input v-model="areaDTOUpdate.name" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="类型" prop="mergerName">
                            <el-input v-model="areaDTOUpdate.mergerName" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="类型" prop="code">
                            <el-input v-model="areaDTOUpdate.code" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="邮政编码" prop="zipCode">
                            <el-input v-model="areaDTOUpdate.zipCode" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="类型" prop="first">
                            <el-input v-model="areaDTOUpdate.first" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="类型" prop="lng">
                            <el-input v-model="areaDTOUpdate.lng" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="类型" prop="lat">
                            <el-input v-model="areaDTOUpdate.lat" placeholder=""></el-input>
                    </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="handleClose('updateForm')" size="mini">取 消</el-button>
                <el-button type="primary" @click="handlerSubmit('updateForm')" size="mini">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import { Message, MessageBox } from 'element-ui'
    import {getInfo,add,update,del,getList} from '@/api/sys/area'

    const tableData = []
    export default {
        name: 'area',
        data() {
            return {
                tableData,
                total : 0,
                addVisible: false,
                updateVisible: false,
                listLoading: false,
                areaQueryModel:{
                    pidEQ:null,
                    nameEQ:null,
                    mergerNameEQ:null,
                    codeEQ:null,
                    zipCodeEQ:null,
                    firstEQ:null,
                    lngEQ:null,
                    latEQ:null,
                    pageNumber: 1,
                    pageSize: 10,
                },
                areaDTOAdd:{
                        pid:null,
                        shortName:null,
                        name:null,
                        mergerName:null,
                        code:null,
                        zipCode:null,
                        first:null,
                        lng:null,
                        lat:null,
                },
                areaDTOUpdate:{
                        pid:null,
                        shortName:null,
                        name:null,
                        mergerName:null,
                        code:null,
                        zipCode:null,
                        first:null,
                        lng:null,
                        lat:null,
                },
                addFormRules: {
//                    ownerCode: [
//                        {required: true, message: '不能为空', trigger: 'blur'},
//                    ],

                },
                updateFormRules: {
    //                    ownerCode: [
    //                        {required: true, message: '不能为空', trigger: 'blur'},
    //                    ],

                },
            }
        },

        created () {
            this.init()
        },

        methods:{
            init() {
                this.getList();
            },

            getList(){
                this.tableData = [];
                this.listLoading = true;
                getList(this.areaQueryModel).then((res) => {
                    res.data.list.forEach(item => {
                        this.tableData.push(item)
                    })
                    this.total = res.data.total;
                    this.listLoading = false;
                });
            },

            getInfo(id){
                this.$router.push({path: '/area/info',query: {id: id}})

            },

            delete(id){
                MessageBox.confirm(
                    '您确定要删除吗',
                    '确定',
                    {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }
                ).then(() => {
                    this.del(id).then((res) => {
                        if (res.code === '20000') {
                            Message({
                                message: "删除成功",
                                type: 'success',
                            });
                        }else{
                            Message({
                                message: "删除失败："+res.message,
                                type: 'error',
                            });
                        }
                    })
                })

            },

            handleCurrentChange(val) {
                this.areaQueryModel.pageNumber = val;
                this.getList();
            },

            updateDialog(val){
                this.updateVisible = true;
                this.areaDTOUpdate = val;
            },

            handlerSubmit(formName){
                this.$refs[formName].validate((valid) => {
                    if (valid){
                        if (formName ==="updateForm"){
                            update(this.areaDTOUpdate).then(response => {
                                Message({
                                    message: "修改成功",
                                    type: 'success',
                                });
                            });
                            this.updateVisible = false;
                            this.init();
                        }else if (formName === "addForm"){
                            add(this.areaDTOAdd).then(response => {
                                Message({
                                    message: "添加成功",
                                    type: 'success',
                                });
                            });
                            this.addVisible = false;
                            this.init();
                        }

                    }
                });
            },

            handleClose(formName) {
                this.addVisible = false;
                this.updateVisible = false;
                this.$refs[formName].resetFields();//将form表单重置
                this.$refs[formName].clearValidate();//将form表单重置
            },

        }
    }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
    .applicaton {
    &-container {
         margin: 30px;
     }
    &-text {
         font-size: 30px;
         line-height: 46px;
     }
    }
</style>
