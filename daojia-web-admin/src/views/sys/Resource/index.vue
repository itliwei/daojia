<template>
    <div class="resource-container">
        <el-col :span="24" class="toolbar">
            <el-form :inline="true" :model="resourceQueryModel"  size="mini">
                <el-form-item :span="6" label="名称">
                    <el-input v-model="resourceQueryModel.nameEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="编码">
                    <el-input v-model="resourceQueryModel.codeEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="菜单ID">
                    <el-input v-model="resourceQueryModel.menuIdEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="是否有效">
                    <el-input v-model="resourceQueryModel.statusEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="是否删除">
                    <el-select v-model="resourceQueryModel.isDelEQ" placeholder="请选择">
                        <el-option
                                v-for="item in this.GLOBAL.isDel"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
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
                <el-table-column prop="name" label="名称"></el-table-column>
                <el-table-column prop="code" label="编码"></el-table-column>
                <el-table-column prop="menuId" label="菜单ID"></el-table-column>
                <el-table-column prop="status" label="是否有效"></el-table-column>
                <el-table-column prop="isDel" label="是否删除"></el-table-column>
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
            <el-form :model="resourceDTOAdd" label-width="80px" ref="addForm" :rules="addFormRules" size="mini">
                    <el-form-item label="名称" prop="name">
                            <el-input v-model="resourceDTOAdd.name" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="编码" prop="code">
                            <el-input v-model="resourceDTOAdd.code" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="菜单ID" prop="menuId">
                            <el-input v-model="resourceDTOAdd.menuId" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="是否有效" prop="status">
                            <el-input v-model="resourceDTOAdd.status" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="是否删除" prop="isDel">
                            <el-select v-model="resourceDTOAdd.isDel" placeholder="请选择">
                                <el-option
                                        v-for="item in this.GLOBAL.isDel"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                    </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="handleClose('addForm')" size="mini">取 消</el-button>
                <el-button type="primary" @click="handlerSubmit('addForm')" size="mini">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="修改" :visible.sync="updateVisible" >
            <el-form :model="resourceDTOUpdate" label-width="80px" ref="updateForm" :rules="updateFormRules">
                    <el-form-item label="名称" prop="name">
                            <el-input v-model="resourceDTOUpdate.name" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="编码" prop="code">
                            <el-input v-model="resourceDTOUpdate.code" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="菜单ID" prop="menuId">
                            <el-input v-model="resourceDTOUpdate.menuId" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="是否有效" prop="status">
                            <el-input v-model="resourceDTOUpdate.status" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="是否删除" prop="isDel">
                            <el-select v-model="resourceDTOUpdate.isDel" placeholder="请选择">
                                <el-option
                                        v-for="item in this.GLOBAL.isDel"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
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
    import {getInfo,add,update,del,getList} from '@/api/sys/resource'

    const tableData = []
    export default {
        name: 'resource',
        data() {
            return {
                tableData,
                total : 0,
                addVisible: false,
                updateVisible: false,
                listLoading: false,
                resourceQueryModel:{
                    nameEQ:null,
                    codeEQ:null,
                    menuIdEQ:null,
                    statusEQ:null,
                    isDelEQ:null,
                    pageNumber: 1,
                    pageSize: 10,
                },
                resourceDTOAdd:{
                        name:null,
                        code:null,
                        menuId:null,
                        status:null,
                        isDel:null,
                },
                resourceDTOUpdate:{
                        name:null,
                        code:null,
                        menuId:null,
                        status:null,
                        isDel:null,
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
                getList(this.resourceQueryModel).then((res) => {
                    res.data.list.forEach(item => {
                        this.tableData.push(item)
                    })
                    this.total = res.data.total;
                    this.listLoading = false;
                });
            },

            getInfo(id){
                this.$router.push({path: '/resource/info',query: {id: id}})

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
                this.resourceQueryModel.pageNumber = val;
                this.getList();
            },

            updateDialog(val){
                this.updateVisible = true;
                this.resourceDTOUpdate = val;
            },

            handlerSubmit(formName){
                this.$refs[formName].validate((valid) => {
                    if (valid){
                        if (formName ==="updateForm"){
                            update(this.resourceDTOUpdate).then(response => {
                                Message({
                                    message: "修改成功",
                                    type: 'success',
                                });
                            });
                            this.updateVisible = false;
                            this.init();
                        }else if (formName === "addForm"){
                            add(this.resourceDTOAdd).then(response => {
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
