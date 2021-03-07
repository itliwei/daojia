<template>
    <div class="body-wrapper">
      <div class="header-wrapper">
        <div>
            <el-form :inline="true" :model="roleQueryModel"  size="mini">
                <el-form-item :span="6" label="名称">
                    <el-input v-model="roleQueryModel.nameEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="编码">
                    <el-input v-model="roleQueryModel.codeEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="数据权限">
                    <el-input v-model="roleQueryModel.dataTypeEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="是否有效">
                    <el-input v-model="roleQueryModel.statusEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="是否删除">
                    <el-select v-model="roleQueryModel.isDelEQ" placeholder="请选择">
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
        </div>
        <div>
            <el-button type="danger" @click="addVisible=true" size="mini">添加</el-button>
        </div>

      </div>
        <el-table :data="tableData" highlight-current-row v-loading="listLoading" style="width: 100%;" size="mini">
            <el-table-column type="index" width="50"></el-table-column>
                <el-table-column prop="name" label="名称"></el-table-column>
                <el-table-column prop="code" label="编码"></el-table-column>
                <el-table-column prop="dataType" label="数据权限"></el-table-column>
                <el-table-column prop="status" label="是否有效"></el-table-column>
                <el-table-column prop="isDel" label="是否删除"></el-table-column>
            <el-table-column
                    label="操作"
                    width="250">
                <template slot-scope="scope">
                    <el-button @click="getInfo(scope.row.id)" type="primary" size="mini">查看</el-button>
                    <el-button @click="updateDialog(scope.row)"  type="warning" size="mini">修改</el-button>
                    <el-button @click="delete(scope.row.id)" type="danger" size="mini">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination  style="float:right" background
                        @current-change="handleCurrentChange"
                        layout="prev, pager, next"
                        :total="total">
        </el-pagination>

        <el-dialog title="添加" :visible.sync="addVisible" >
            <el-form :model="roleDTOAdd" label-width="80px" ref="addForm" :rules="addFormRules" size="mini">
                    <el-form-item label="名称" prop="name">
                            <el-input v-model="roleDTOAdd.name" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="编码" prop="code">
                            <el-input v-model="roleDTOAdd.code" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="数据权限" prop="dataType">
                            <el-input v-model="roleDTOAdd.dataType" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="是否有效" prop="status">
                            <el-input v-model="roleDTOAdd.status" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="是否删除" prop="isDel">
                            <el-select v-model="roleDTOAdd.isDel" placeholder="请选择">
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
            <el-form :model="roleDTOUpdate" label-width="80px" ref="updateForm" :rules="updateFormRules">
                    <el-form-item label="名称" prop="name">
                            <el-input v-model="roleDTOUpdate.name" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="编码" prop="code">
                            <el-input v-model="roleDTOUpdate.code" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="数据权限：ALL:1,全部;THIS_LEVEL:2,本级;THIS_LEVEL_CHILDREN:3,本级以及子级;CUSTOMIZE:4,自定义;SELF:5,个人" prop="dataType">
                            <el-input v-model="roleDTOUpdate.dataType" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="是否有效" prop="status">
                            <el-input v-model="roleDTOUpdate.status" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="是否删除" prop="isDel">
                            <el-select v-model="roleDTOUpdate.isDel" placeholder="请选择">
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
    import {getInfo,add,update,del,getList} from '@/api/sys/role'

    const tableData = []
    export default {
        name: 'role',
        data() {
            return {
                tableData,
                total : 0,
                addVisible: false,
                updateVisible: false,
                listLoading: false,
                roleQueryModel:{
                    nameEQ:null,
                    codeEQ:null,
                    dataTypeEQ:null,
                    statusEQ:null,
                    isDelEQ:null,
                    pageNumber: 1,
                    pageSize: 10,
                },
                roleDTOAdd:{
                        name:null,
                        code:null,
                        dataType:null,
                        status:null,
                        isDel:null,
                },
                roleDTOUpdate:{
                        name:null,
                        code:null,
                        dataType:null,
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
                getList(this.roleQueryModel).then((res) => {
                    res.data.list.forEach(item => {
                        this.tableData.push(item)
                    })
                    this.total = res.data.total;
                    this.listLoading = false;
                });
            },

            getInfo(id){
                this.$router.push({path: '/role/info',query: {id: id}})

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
                this.roleQueryModel.pageNumber = val;
                this.getList();
            },

            updateDialog(val){
                this.updateVisible = true;
                this.roleDTOUpdate = val;
            },

            handlerSubmit(formName){
                this.$refs[formName].validate((valid) => {
                    if (valid){
                        if (formName ==="updateForm"){
                            update(this.roleDTOUpdate).then(response => {
                                Message({
                                    message: "修改成功",
                                    type: 'success',
                                });
                            });
                            this.updateVisible = false;
                            this.init();
                        }else if (formName === "addForm"){
                            add(this.roleDTOAdd).then(response => {
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
