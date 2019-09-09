package com.zhidiantech.orangesample.httptest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

//import com.zhidiantech.orangehttp.BuildConfig;
//import com.zhidiantech.orangehttp.http.OfHttpUtil;
//import com.zhidiantech.orangehttp.observer.OrangeFrameObserver;
//import com.zhidiantech.orangehttp.observer.TransformControl;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import retrofit2.http.GET;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 芝点科技 wen
 * -----------------------------------------------------------------
 * Create: 2019/4/16 下午5:57
 * Changes (from 2019/4/16)
 * -----------------------------------------------------------------
 */
public class HttpSampleActivity  extends AppCompatActivity {

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //以下是示例代码
//
//        //1.在你的Application中初始化配置
//        OfHttpUtil.init(getApplication());
//                OfHttpUtil.getInstance().startConfig().setBaseUrl("http://www.xx.cn")
//                .setLog(BuildConfig.DEBUG)
//                .setConnectTimeout(10000);
//
//       //2.定义自己的BaseObserver，使其保持泛型化
//    }
//
//    //定义自己的BaseObserver
//    abstract class BaseObserver<T> extends OrangeFrameObserver<T>{}
//
//    //3.定义项目稳定的响应体结构
//    class BaseResponse<D>{
//        private int code;
//        private String msg;
//        private D data;
//
//        public int getCode() {
//            return code;
//        }
//
//        public void setCode(int code) {
//            this.code = code;
//        }
//
//        public String getMsg() {
//            return msg;
//        }
//
//        public void setMsg(String msg) {
//            this.msg = msg;
//        }
//
//        public D getData() {
//            return data;
//        }
//
//        public void setData(D data) {
//            this.data = data;
//        }
//    }
//
//    //4.在需要发起网络请求的地方
//
//    //接口API
//    interface HttpSampleAPI{
//        /**
//         * 获取banner数据
//         * @return
//         */
//        @GET("welcome")
//        Observable<BaseResponse<String>> getWelcomeData();
//    }
//
//    //4.1 创建观察者
//    BaseObserver<BaseResponse<String>> observer=new BaseObserver<BaseResponse<String>>() {
//        @Override
//        protected void _onSub(Disposable d) {
//
//        }
//
//        @Override
//        protected void _onNext(BaseResponse<String> stringBaseResponse) {
//            if (stringBaseResponse.getCode()==200){
//                //正确逻辑响应
//            }else {
//                //异常逻辑响应
//            }
//        }
//
//        @Override
//        protected void _onError(String errorMsg) {
//
//        }
//
//        @Override
//        protected void showDialog() {
//
//        }
//
//        @Override
//        protected void hideDialog() {
//
//        }
//    };
//
//    //4.2 创建API
//    HttpSampleAPI mApi=OfHttpUtil.createApi(HttpSampleAPI.class);
//    //4.3发起请求
//    private void getData(){
//        mApi.getWelcomeData().compose(TransformControl.<BaseResponse<String>>switchSchedulers())
//                .subscribe(observer);
//    }
}
