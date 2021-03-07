import request from '@/utils/request'

export function getList(params) {
    return request({
        url: '/sys/tenant/page/query',
        method: 'POST',
        data: params
    })
}

export function getInfo(id) {
    return request({
        url: '/sys/tenant/info/'+id,
        method: 'GET',
    })
}


export function update(params) {
    return request({
        url: '/sys/tenant/update',
        method: 'POST',
        data: params
    })
}

export function add(params) {
    return request({
        url: '/sys/tenant/save',
        method: 'POST',
        data: params
    })
}

export function del(id) {
    return request({
        url: '/sys/tenant/delete/'+id,
        method: 'GET',
    })
}




