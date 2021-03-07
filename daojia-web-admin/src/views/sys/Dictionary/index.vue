<template>
    <div class="dictionary-container">
        <el-col :span="24" class="toolbar">
            <el-form :inline="true" :model="dictionaryQueryModel"  size="mini">
                <el-form-item :span="6" label="父ID">
                    <el-input v-model="dictionaryQueryModel.parentIdEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="编码">
                    <el-input v-model="dictionaryQueryModel.dicCodeEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="类型">
                    <el-input v-model="dictionaryQueryModel.dicTypeEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="排序">
                    <el-input v-model="dictionaryQueryModel.sortValueEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="是否有效">
                    <el-input v-model="dictionaryQueryModel.statusEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="是否删除">
                    <el-select v-model="dictionaryQueryModel.isDelEQ" placeholder="请选择">
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
                <el-table-column prop="parentId" label="父ID"></el-table-column>
                <el-table-column prop="dicCode" label="编码"></el-table-column>
                <el-table-column prop="dicValue" label="数值"></el-table-column>
                <el-table-column prop="dicType" label="类型"></el-table-column>
                <el-table-column prop="sortValue" label="排序"></el-table-column>
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
            <el-form :model="dictionaryDTOAdd" label-width="80px" ref="addForm" :rules="addFormRules" size="mini">
                    <el-form-item label="父ID" prop="parentId">
                            <el-input v-model="dictionaryDTOAdd.parentId" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="编码" prop="dicCode">
                            <el-input v-model="dictionaryDTOAdd.dicCode" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="数值" prop="dicValue">
                            <el-input v-model="dictionaryDTOAdd.dicValue" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="类型" prop="dicType">
                            <el-input v-model="dictionaryDTOAdd.dicType" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="排序" prop="sortValue">
                            <el-input v-model="dictionaryDTOAdd.sortValue" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="是否有效" prop="status">
                            <el-input v-model="dictionaryDTOAdd.status" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="是否删除" prop="isDel">
                            <el-select v-model="dictionaryDTOAdd.isDel" placeholder="请选择">
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
            <el-form :model="dictionaryDTOUpdate" label-width="80px" ref="updateForm" :rules="updateFormRules">
                    <el-form-item label="父ID" prop="parentId">
                            <el-input v-model="dictionaryDTOUpdate.parentId" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="编码" prop="dicCode">
                            <el-input v-model="dictionaryDTOUpdate.dicCode" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="数值" prop="dicValue">
                            <el-input v-model="dictionaryDTOUpdate.dicValue" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="类型" prop="dicType">
                            <el-input v-model="dictionaryDTOUpdate.dicType" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="排序" prop="sortValue">
                            <el-input v-model="dictionaryDTOUpdate.sortValue" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="是否有效" prop="status">
                            <el-input v-model="dictionaryDTOUpdate.status" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="是否删除" prop="isDel">
                            <el-select v-model="dictionaryDTOUpdate.isDel" placeholder="请选择">
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
    import {getInfo,add,update,del,getList} from '@/api/sys/dictionary'

    const tableData = []
    export default {
        name: 'dictionary',
        data() {
            return {
                tableData,
                total : 0,
                addVisible: false,
                updateVisible: false,
                listLoading: false,
                dictionaryQueryModel:{
                    parentIdEQ:null,
                    dicCodeEQ:null,
                    dicTypeEQ:null,
                    sortValueEQ:null,
                    statusEQ:null,
                    isDelEQ:null,
                    pageNumber: 1,
                    pageSize: 10,
                },
                dictionaryDTOAdd:{
                        parentId:null,
                        dicCode:null,
                        dicValue:null,
                        dicType:null,
                        sortValue:null,
                        status:null,
                        isDel:null,
                },
                dictionaryDTOUpdate:{
                        parentId:null,
                        dicCode:null,
                        dicValue:null,
                        dicType:null,
                        sortValue:null,
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
                getList(this.dictionaryQueryModel).then((res) => {
                    res.data.list.forEach(item => {
                        this.tableData.push(item)
                    })
                    this.total = res.data.total;
                    this.listLoading = false;
                });
            },

            getInfo(id){
                this.$router.push({path: '/dictionary/info',query: {id: id}})

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
                this.dictionaryQueryModel.pageNumber = val;
                this.getList();
            },

            updateDialog(val){
                this.updateVisible = true;
                this.dictionaryDTOUpdate = val;
            },

            handlerSubmit(formName){
                this.$refs[formName].validate((valid) => {
                    if (valid){
                        if (formName ==="updateForm"){
                            update(this.dictionaryDTOUpdate).then(response => {
                                Message({
                                    message: "修改成功",
                                    type: 'success',
                                });
                            });
                            this.updateVisible = false;
                            this.init();
                        }else if (formName === "addForm"){
                            add(this.dictionaryDTOAdd).then(response => {
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
