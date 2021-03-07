
const isDel = [{
  value: 0,
  label: '未删除'
}, {
  value: 1,
  label: '删除'
}];

const isTrue = [{
  value: 0,
  label: '否'
}, {
  value: 1,
  label: '是'
}];


const taskStatus = [{
  value: -1,
  label: '未提交'
},{
  value: 0,
  label: '已提交'
}, {
  value: 1,
  label: '已暂停'
}, {
  value: 2,
  label: '运行中'
}];

const alarmWay = [{
  value: 0,
  label: '无告警'
},{
  value: 1,
  label: '钉钉'
}];


const alarmStatus = [{
  value: 0,
  label: '暂停'
},{
  value: 1,
  label: '启动'
}];

const rateTimeOptions = [
  {
    value: 15,
    label: '每15分钟'
  },
  {
    value: 30,
    label: '每30分钟'
  }, {
    value: 60,
    label: '每60分钟'
  }
];

const intervalTimeOptions = [
  {
    value: 15,
    label: '每15分钟'
  },
  {
    value: 30,
    label: '每30分钟'
  }, {
    value: 60,
    label: '每60分钟'
  },
  {
    value: 240,
    label: '每4小时'
  },
  {
    value: 480,
    label: '每8小时'
  }, {
    value: 1440,
    label: '每24小时'
  }
];

const intervalTimeSecondOptions = [
  {
    value: 0,
    label: '逐条'
  },
  {
    value: 1,
    label: '秒级'
  },{
    value: 5,
    label: '5秒'
  },
  {
    value: 15,
    label: '15秒'
  },{
    value: 30,
    label: '30秒'
  },
  {
    value: 60,
    label: '分钟级别'
  },
  {
    value: 900,
    label: '每15分钟'
  },
  {
    value: 3600,
    label: '每60分钟'
  }
];

const baseValueOptions = [
  {
    value: 1,
    label: '前1天相同时间段的数值'
  },
  {
    value: 3,
    label: '前3天相同时间段的数值'
  }, {
    value: 7,
    label: '前7天相同时间段的数值'
  }
];

const jobCronOptions = [
    {
      value: "second",
      label: '秒'
    },
  {
    value: "minute",
    label: '分'
  }, {
    value: "hour",
    label: '时'
  }, {
    value: "day",
    label: '天'
  }, {
    value: "week",
    label: '周'
  }, {
    value: "month",
    label: '月'
  }, {
    value: "year",
    label: '年'
  }
];

const operOptions=[
  {
    value: "=",
    label: '等于'
  }, {
    value: "<>",
    label: '不等于'
  }, {
    value: ">",
    label: '大于'
  }, {
    value: ">=",
    label: '大于等于'
  }, {
    value: "<",
    label: '小于'
  }, {
    value: "<=",
    label: '小于等于'
  }
];

const andOrOptions=[
  {
    value: "&&",
    label: '和'
  }, {
    value: "||",
    label: '或'
  }
];

const monitorIndex=[
  {
    value: "all",
    label: 'ALL'
  }, {
    value: "qps",
    label: 'QPS'
  }, {
    value: "rt",
    label: '响应时长'
  }, {
    value: "error_rate",
    label: '错误率'
  }
];

const monitorType=[
  {
    value: 0,
    label: '应用'
  }, {
    value: 1,
    label: 'URL'
  }
];

const  compOptions = [
  {
    value: "COUNT",
    label: '总数'
  },{
    value: "SUM",
    label: '求和'
  }, {
    value: "AVG",
    label: '平均值'
  }, {
    value: "MAX",
    label: '最大值'
  }, {
    value: "MIN",
    label: '最小值'
  }
];
const  fieldTypes = [
  {
    value: "BIGINT",
    label: 'BIGINT'
  }, {
    value: "DOUBLE",
    label: 'DOUBLE'
  }
];

var operationTypes = Object.freeze({
  "COUNT": {
    value: "COUNT",
    label: '总数'
  },
  "SUM": {
    value: "SUM",
    label: '求和'
  },
  "AVG": {
    value: "AVG",
    label: '平均值'
  },
  "MAX": {
    value: "MAX",
    label: '最大值'
  },
  "MIN" : {
    value: "MIN",
    label: '最小值'
  }
});

  export default {
    isDel,
    isTrue,
    taskStatus,
    alarmWay,
    alarmStatus,
    rateTimeOptions,
    intervalTimeOptions,
    intervalTimeSecondOptions,
    baseValueOptions,
    andOrOptions,
    jobCronOptions,
    operOptions,
    monitorType,
    monitorIndex,
    compOptions,
    fieldTypes,
    operationTypes
  }
