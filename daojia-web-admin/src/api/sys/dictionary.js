import request from '@/utils/request'

export function getList(params) {
    return request({
        url: '/sys/dictionary/page/query',
        method: 'POST',
        data: params
    })
}

export function getInfo(id) {
    return request({
        url: '/sys/dictionary/info/'+id,
        method: 'GET',
    })
}


export function update(params) {
    return request({
        url: '/sys/dictionary/update',
        method: 'POST',
        data: params
    })
}

export function add(params) {
    return request({
        url: '/sys/dictionary/save',
        method: 'POST',
        data: params
    })
}

export function del(id) {
    return request({
        url: '/sys/dictionary/delete/'+id,
        method: 'GET',
    })
}




