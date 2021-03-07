import request from '@/utils/request'

export function getList(params) {
    return request({
        url: '/sys/role/authority/page/query',
        method: 'POST',
        data: params
    })
}

export function getInfo(id) {
    return request({
        url: '/sys/role/authority/info/'+id,
        method: 'GET',
    })
}


export function update(params) {
    return request({
        url: '/sys/role/authority/update',
        method: 'POST',
        data: params
    })
}

export function add(params) {
    return request({
        url: '/sys/role/authority/save',
        method: 'POST',
        data: params
    })
}

export function del(id) {
    return request({
        url: '/sys/role/authority/delete/'+id,
        method: 'GET',
    })
}




