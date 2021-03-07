<template>
    <div class="tenant-container">
        <el-col :span="24" class="toolbar">
            <el-form :inline="true" :model="tenantQueryModel"  size="mini">
                <el-form-item :span="6" label="名称">
                    <el-input v-model="tenantQueryModel.nameEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="用户名称">
                    <el-input v-model="tenantQueryModel.codeEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="过期时间">
                    <el-date-picker
                            v-model="tenantQueryModel.expirationTimeEQ"
                            type="datetime"
                            placeholder="选择日期时间">
                    </el-date-picker>
                </el-form-item>
                <el-form-item :span="6" label="状态：0：注册 1：试用 2：有效 3：过期">
                    <el-input v-model="tenantQueryModel.statusEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="是否删除">
                    <el-select v-model="tenantQueryModel.isDelEQ" placeholder="请选择">
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
                <el-table-column prop="code" label="用户名称"></el-table-column>
                <el-table-column prop="description" label="描述"></el-table-column>
                <el-table-column prop="logo" label="用户名称"></el-table-column>
                <el-table-column prop="manager" label="负责人姓名"></el-table-column>
                <el-table-column prop="managerPhone" label="负责人手机号"></el-table-column>
                <el-table-column prop="expirationTime" label="过期时间"></el-table-column>
                <el-table-column prop="status" label="状态：0：注册 1：试用 2：有效 3：过期"></el-table-column>
                <el-table-column prop="isDel" label="是否删除"></el-table-column>
                <el-table-column prop="createTime" label="创建时间"></el-table-column>
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
            <el-form :model="tenantDTOAdd" label-width="80px" ref="addForm" :rules="addFormRules" size="mini">
                    <el-form-item label="名称" prop="name">
                            <el-input v-model="tenantDTOAdd.name" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="用户名称" prop="code">
                            <el-input v-model="tenantDTOAdd.code" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="描述" prop="description">
                            <el-input v-model="tenantDTOAdd.description" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="用户名称" prop="logo">
                            <el-input v-model="tenantDTOAdd.logo" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="负责人姓名" prop="manager">
                            <el-input v-model="tenantDTOAdd.manager" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="负责人手机号" prop="managerPhone">
                            <el-input v-model="tenantDTOAdd.managerPhone" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="过期时间" prop="expirationTime">
                            <el-date-picker
                                    v-model="tenantDTOAdd.expirationTime"
                                    type="datetime"
                                    placeholder="选择日期时间">
                            </el-date-picker>
                    </el-form-item>
                    <el-form-item label="状态：0：注册 1：试用 2：有效 3：过期" prop="status">
                            <el-input v-model="tenantDTOAdd.status" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="是否删除" prop="isDel">
                            <el-select v-model="tenantDTOAdd.isDel" placeholder="请选择">
                                <el-option
                                        v-for="item in this.GLOBAL.isDel"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                    </el-form-item>
                    <el-form-item label="创建时间" prop="createTime">
                            <el-date-picker
                                    v-model="tenantDTOAdd.createTime"
                                    type="datetime"
                                    placeholder="选择日期时间">
                            </el-date-picker>
                    </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="handleClose('addForm')" size="mini">取 消</el-button>
                <el-button type="primary" @click="handlerSubmit('addForm')" size="mini">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="修改" :visible.sync="updateVisible" >
            <el-form :model="tenantDTOUpdate" label-width="80px" ref="updateForm" :rules="updateFormRules">
                    <el-form-item label="名称" prop="name">
                            <el-input v-model="tenantDTOUpdate.name" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="用户名称" prop="code">
                            <el-input v-model="tenantDTOUpdate.code" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="描述" prop="description">
                            <el-input v-model="tenantDTOUpdate.description" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="用户名称" prop="logo">
                            <el-input v-model="tenantDTOUpdate.logo" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="负责人姓名" prop="manager">
                            <el-input v-model="tenantDTOUpdate.manager" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="负责人手机号" prop="managerPhone">
                            <el-input v-model="tenantDTOUpdate.managerPhone" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="过期时间" prop="expirationTime">
                            <el-date-picker
                                    v-model="tenantDTOUpdate.expirationTime"
                                    type="datetime"
                                    placeholder="选择日期时间">
                            </el-date-picker>
                    </el-form-item>
                    <el-form-item label="状态：0：注册 1：试用 2：有效 3：过期" prop="status">
                            <el-input v-model="tenantDTOUpdate.status" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="是否删除" prop="isDel">
                            <el-select v-model="tenantDTOUpdate.isDel" placeholder="请选择">
                                <el-option
                                        v-for="item in this.GLOBAL.isDel"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                    </el-form-item>
                    <el-form-item label="创建时间" prop="createTime">
                            <el-date-picker
                                    v-model="tenantDTOUpdate.createTime"
                                    type="datetime"
                                    placeholder="选择日期时间">
                            </el-date-picker>
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
    import {getInfo,add,update,del,getList} from '@/api/sys/tenant'

    const tableData = []
    export default {
        name: 'tenant',
        data() {
            return {
                tableData,
                total : 0,
                addVisible: false,
                updateVisible: false,
                listLoading: false,
                tenantQueryModel:{
                    nameEQ:null,
                    codeEQ:null,
                    expirationTimeEQ:null,
                    statusEQ:null,
                    isDelEQ:null,
                    pageNumber: 1,
                    pageSize: 10,
                },
                tenantDTOAdd:{
                        name:null,
                        code:null,
                        description:null,
                        logo:null,
                        manager:null,
                        managerPhone:null,
                        expirationTime:null,
                        status:null,
                        isDel:null,
                        createTime:null,
                },
                tenantDTOUpdate:{
                        name:null,
                        code:null,
                        description:null,
                        logo:null,
                        manager:null,
                        managerPhone:null,
                        expirationTime:null,
                        status:null,
                        isDel:null,
                        createTime:null,
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
                getList(this.tenantQueryModel).then((res) => {
                    res.data.list.forEach(item => {
                        this.tableData.push(item)
                    })
                    this.total = res.data.total;
                    this.listLoading = false;
                });
            },

            getInfo(id){
                this.$router.push({path: '/tenant/info',query: {id: id}})

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
                this.tenantQueryModel.pageNumber = val;
                this.getList();
            },

            updateDialog(val){
                this.updateVisible = true;
                this.tenantDTOUpdate = val;
            },

            handlerSubmit(formName){
                this.$refs[formName].validate((valid) => {
                    if (valid){
                        if (formName ==="updateForm"){
                            update(this.tenantDTOUpdate).then(response => {
                                Message({
                                    message: "修改成功",
                                    type: 'success',
                                });
                            });
                            this.updateVisible = false;
                            this.init();
                        }else if (formName === "addForm"){
                            add(this.tenantDTOAdd).then(response => {
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
