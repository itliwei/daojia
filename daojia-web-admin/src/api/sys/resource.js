import request from '@/utils/request'

export function getList(params) {
    return request({
        url: '/sys/resource/page/query',
        method: 'POST',
        data: params
    })
}

export function getInfo(id) {
    return request({
        url: '/sys/resource/info/'+id,
        method: 'GET',
    })
}


export function update(params) {
    return request({
        url: '/sys/resource/update',
        method: 'POST',
        data: params
    })
}

export function add(params) {
    return request({
        url: '/sys/resource/save',
        method: 'POST',
        data: params
    })
}

export function del(id) {
    return request({
        url: '/sys/resource/delete/'+id,
        method: 'GET',
    })
}




