import request from '@/utils/request'

export function getList(params) {
    return request({
        url: '/sys/area/page/query',
        method: 'POST',
        data: params
    })
}

export function getInfo(id) {
    return request({
        url: '/sys/area/info/'+id,
        method: 'GET',
    })
}


export function update(params) {
    return request({
        url: '/sys/area/update',
        method: 'POST',
        data: params
    })
}

export function add(params) {
    return request({
        url: '/sys/area/save',
        method: 'POST',
        data: params
    })
}

export function del(id) {
    return request({
        url: '/sys/area/delete/'+id,
        method: 'GET',
    })
}




