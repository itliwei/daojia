import request from '@/utils/request'

export function getList(params) {
    return request({
        url: '/sys/user/role/page/query',
        method: 'POST',
        data: params
    })
}

export function getInfo(id) {
    return request({
        url: '/sys/user/role/info/'+id,
        method: 'GET',
    })
}


export function update(params) {
    return request({
        url: '/sys/user/role/update',
        method: 'POST',
        data: params
    })
}

export function add(params) {
    return request({
        url: '/sys/user/role/save',
        method: 'POST',
        data: params
    })
}

export function del(id) {
    return request({
        url: '/sys/user/role/delete/'+id,
        method: 'GET',
    })
}




