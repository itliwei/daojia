import request from '@/utils/request'

export function getList(params) {
    return request({
        url: '/sys/user/page/query',
        method: 'POST',
        data: params
    })
}

export function getInfo(id) {
    return request({
        url: '/sys/user/info/'+id,
        method: 'GET',
    })
}


export function update(params) {
    return request({
        url: '/sys/user/update',
        method: 'POST',
        data: params
    })
}

export function add(params) {
    return request({
        url: '/sys/user/save',
        method: 'POST',
        data: params
    })
}

export function del(id) {
    return request({
        url: '/sys/user/delete/'+id,
        method: 'GET',
    })
}




