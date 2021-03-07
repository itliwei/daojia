<template>
    <div class="sysUser-container">
        <el-col :span="24" class="toolbar">
            <el-form :inline="true" :model="sysUserQueryModel"  size="mini">
                <el-form-item :span="6" label="名称">
                    <el-input v-model="sysUserQueryModel.nameEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="用户名称">
                    <el-input v-model="sysUserQueryModel.usernameEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="密码">
                    <el-input v-model="sysUserQueryModel.passwordEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="部门">
                    <el-input v-model="sysUserQueryModel.orgIdEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="岗位">
                    <el-input v-model="sysUserQueryModel.stationIdEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="手机号">
                    <el-input v-model="sysUserQueryModel.phoneEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="邮箱">
                    <el-input v-model="sysUserQueryModel.emailEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="性别">
                    <el-input v-model="sysUserQueryModel.sexEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="头像">
                    <el-input v-model="sysUserQueryModel.avatarEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="是否有效">
                    <el-input v-model="sysUserQueryModel.statusEQ" placeholder=""></el-input>
                </el-form-item>
                <el-form-item :span="6" label="是否删除">
                    <el-select v-model="sysUserQueryModel.isDelEQ" placeholder="请选择">
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
                <el-table-column prop="username" label="用户名称"></el-table-column>
                <el-table-column prop="password" label="密码"></el-table-column>
                <el-table-column prop="orgId" label="部门"></el-table-column>
                <el-table-column prop="stationId" label="岗位"></el-table-column>
                <el-table-column prop="phone" label="手机号"></el-table-column>
                <el-table-column prop="email" label="邮箱"></el-table-column>
                <el-table-column prop="sex" label="性别"></el-table-column>
                <el-table-column prop="avatar" label="头像"></el-table-column>
                <el-table-column prop="roles" label="角色"></el-table-column>
                <el-table-column prop="status" label="是否有效"></el-table-column>
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
            <el-form :model="sysUserDTOAdd" label-width="80px" ref="addForm" :rules="addFormRules" size="mini">
                    <el-form-item label="名称" prop="name">
                            <el-input v-model="sysUserDTOAdd.name" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="用户名称" prop="username">
                            <el-input v-model="sysUserDTOAdd.username" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                            <el-input v-model="sysUserDTOAdd.password" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="部门" prop="orgId">
                            <el-input v-model="sysUserDTOAdd.orgId" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="岗位" prop="stationId">
                            <el-input v-model="sysUserDTOAdd.stationId" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="手机号" prop="phone">
                            <el-input v-model="sysUserDTOAdd.phone" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱" prop="email">
                            <el-input v-model="sysUserDTOAdd.email" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="性别" prop="sex">
                            <el-input v-model="sysUserDTOAdd.sex" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="头像" prop="avatar">
                            <el-input v-model="sysUserDTOAdd.avatar" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="角色" prop="roles">
                            <el-input v-model="sysUserDTOAdd.roles" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="是否有效" prop="status">
                            <el-input v-model="sysUserDTOAdd.status" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="是否删除" prop="isDel">
                            <el-select v-model="sysUserDTOAdd.isDel" placeholder="请选择">
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
                                    v-model="sysUserDTOAdd.createTime"
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
            <el-form :model="sysUserDTOUpdate" label-width="80px" ref="updateForm" :rules="updateFormRules">
                    <el-form-item label="名称" prop="name">
                            <el-input v-model="sysUserDTOUpdate.name" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="用户名称" prop="username">
                            <el-input v-model="sysUserDTOUpdate.username" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                            <el-input v-model="sysUserDTOUpdate.password" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="部门" prop="orgId">
                            <el-input v-model="sysUserDTOUpdate.orgId" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="岗位" prop="stationId">
                            <el-input v-model="sysUserDTOUpdate.stationId" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="手机号" prop="phone">
                            <el-input v-model="sysUserDTOUpdate.phone" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱" prop="email">
                            <el-input v-model="sysUserDTOUpdate.email" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="性别" prop="sex">
                            <el-input v-model="sysUserDTOUpdate.sex" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="头像" prop="avatar">
                            <el-input v-model="sysUserDTOUpdate.avatar" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="角色" prop="roles">
                            <el-input v-model="sysUserDTOUpdate.roles" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="是否有效" prop="status">
                            <el-input v-model="sysUserDTOUpdate.status" placeholder=""></el-input>
                    </el-form-item>
                    <el-form-item label="是否删除" prop="isDel">
                            <el-select v-model="sysUserDTOUpdate.isDel" placeholder="请选择">
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
                                    v-model="sysUserDTOUpdate.createTime"
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
    import {getInfo,add,update,del,getList} from '@/api/sys/sysUser'

    const tableData = []
    export default {
        name: 'sysUser',
        data() {
            return {
                tableData,
                total : 0,
                addVisible: false,
                updateVisible: false,
                listLoading: false,
                sysUserQueryModel:{
                    nameEQ:null,
                    usernameEQ:null,
                    passwordEQ:null,
                    orgIdEQ:null,
                    stationIdEQ:null,
                    phoneEQ:null,
                    emailEQ:null,
                    sexEQ:null,
                    avatarEQ:null,
                    statusEQ:null,
                    isDelEQ:null,
                    pageNumber: 1,
                    pageSize: 10,
                },
                sysUserDTOAdd:{
                        name:null,
                        username:null,
                        password:null,
                        orgId:null,
                        stationId:null,
                        phone:null,
                        email:null,
                        sex:null,
                        avatar:null,
                        roles:null,
                        status:null,
                        isDel:null,
                        createTime:null,
                },
                sysUserDTOUpdate:{
                        name:null,
                        username:null,
                        password:null,
                        orgId:null,
                        stationId:null,
                        phone:null,
                        email:null,
                        sex:null,
                        avatar:null,
                        roles:null,
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
                getList(this.sysUserQueryModel).then((res) => {
                    res.data.list.forEach(item => {
                        this.tableData.push(item)
                    })
                    this.total = res.data.total;
                    this.listLoading = false;
                });
            },

            getInfo(id){
                this.$router.push({path: '/sysUser/info',query: {id: id}})

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
                this.sysUserQueryModel.pageNumber = val;
                this.getList();
            },

            updateDialog(val){
                this.updateVisible = true;
                this.sysUserDTOUpdate = val;
            },

            handlerSubmit(formName){
                this.$refs[formName].validate((valid) => {
                    if (valid){
                        if (formName ==="updateForm"){
                            update(this.sysUserDTOUpdate).then(response => {
                                Message({
                                    message: "修改成功",
                                    type: 'success',
                                });
                            });
                            this.updateVisible = false;
                            this.init();
                        }else if (formName === "addForm"){
                            add(this.sysUserDTOAdd).then(response => {
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
