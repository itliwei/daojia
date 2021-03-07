import request from '@/utils/request'

export function getList(params) {
    return request({
        url: '/sys/role/page/query',
        method: 'POST',
        data: params
    })
}

export function getInfo(id) {
    return request({
        url: '/sys/role/info/'+id,
        method: 'GET',
    })
}


export function update(params) {
    return request({
        url: '/sys/role/update',
        method: 'POST',
        data: params
    })
}

export function add(params) {
    return request({
        url: '/sys/role/save',
        method: 'POST',
        data: params
    })
}

export function del(id) {
    return request({
        url: '/sys/role/delete/'+id,
        method: 'GET',
    })
}




