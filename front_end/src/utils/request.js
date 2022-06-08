import axios from 'axios'

const request = axios.create({
    
    baseURL: 'http://localhost:9090',
    timeout: 5000
})

// request 拦截器
// 可以自请求发送前对请求做一些处理
// 比如统一加token，对请求参数统一加密
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    let type = config.url.split("/")[1];

    // 根据路由判断,后台地址是哪个
    switch (type) {

      case "trend":
            config.baseURL = "http://localhost:9091";
            break;
      case "datetime":
            config.baseURL = "http://localhost:9093";
            break;
      case "fund":
            config.baseURL = "http://localhost:9093";
            break;
       case "settlement":
            config.baseURL = "http://localhost:9093";
            break;
            case "settlement":
                config.baseURL = "http://localhost:9093";
                break;
      case "queries":
        config.baseURL = "http://localhost:9090";
        break;
        case "redem":
            config.baseURL = "http://localhost:9090";
            break;
            case "delete":
            config.baseURL = "http://localhost:9090";
            break;
            case "bank":
                config.baseURL = "http://localhost:9090";
                break;
      case "product":
        config.baseURL = "http://localhost:9091";
        break;
        case "user":
            config.baseURL = "http://localhost:9091";
            break;
        case "login":
           config.baseURL = "http://localhost:9091";
           break;
        case "purchase":
           config.baseURL = "http://localhost:9092";
           break;
        
    }


    // config.headers['token'] = user.token;  // 设置请求头
    return config
}, error => {
    return Promise.reject(error)
});

// response 拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
        let res = response.data;
        // 如果是返回的文件
        if (response.config.responseType === 'blob') {
            return res
        }
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        return res;
    },
    error => {
        console.log('err' + error) // for debug
        return Promise.reject(error)
    }
)


export default request
