import request from '@/utils/request'

export function getList(params) {
    return request({
        url: '/sys/menu/page/query',
        method: 'POST',
        data: params
    })
}

export function getInfo(id) {
    return request({
        url: '/sys/menu/info/'+id,
        method: 'GET',
    })
}


export function update(params) {
    return request({
        url: '/sys/menu/update',
        method: 'POST',
        data: params
    })
}

export function add(params) {
    return request({
        url: '/sys/menu/save',
        method: 'POST',
        data: params
    })
}

export function del(id) {
    return request({
        url: '/sys/menu/delete/'+id,
        method: 'GET',
    })
}




