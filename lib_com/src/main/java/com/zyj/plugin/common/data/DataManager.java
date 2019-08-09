package com.zyj.plugin.common.data;


import com.zyj.plugin.common.data.bean.AddressInfoBean;
import com.zyj.plugin.common.data.bean.BaseResponse;
import com.zyj.plugin.common.data.bean.BaseResponseV2;
import com.zyj.plugin.common.data.bean.CarNumberInfoBean;
import com.zyj.plugin.common.data.bean.CityInfoBean;
import com.zyj.plugin.common.data.bean.HomeBean;
import com.zyj.plugin.common.data.bean.NewsBean;
import com.zyj.plugin.common.data.bean.NewsDetailBean;
import com.zyj.plugin.common.data.bean.NoPayOrderBean;
import com.zyj.plugin.common.data.bean.OnLinePayOrderBean;
import com.zyj.plugin.common.data.bean.OrderBean;
import com.zyj.plugin.common.data.bean.OrderDetailBean;
import com.zyj.plugin.common.data.bean.ParkBean;
import com.zyj.plugin.common.data.bean.ParkPriiceBean;
import com.zyj.plugin.common.data.bean.ParkingInfoBean;
import com.zyj.plugin.common.data.bean.PassengerInfoBean;
import com.zyj.plugin.common.data.bean.PassengerInfoDetail;
import com.zyj.plugin.common.data.bean.PayHistoryBean;
import com.zyj.plugin.common.data.bean.PayInfoBean;
import com.zyj.plugin.common.data.bean.ReseverPayOrderBean;
import com.zyj.plugin.common.data.bean.UserInfo;
import com.zyj.plugin.common.data.bean.VenueBean;
import com.zyj.plugin.common.data.bean.VenueDetailBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

public class DataManager implements ApiService {

    private ApiService apiService;

    public DataManager(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<BaseResponse<List<NewsBean>>> getNewsList(int scenicId) {
        return apiService.getNewsList(scenicId);
    }

    @Override
    public Observable<BaseResponse<Boolean>> strategyInfoVote(int id) {
        return apiService.strategyInfoVote(id);
    }

    @Override
    public Observable<BaseResponse<NewsDetailBean>> getNewsDetail(int id) {
        return apiService.getNewsDetail(id);
    }

    @Override
    public Observable<BaseResponseV2<List<OrderBean>>> getOrderList(int offset, int limit, int userId) {
        return apiService.getOrderList(offset, limit, userId);
    }

    @Override
    public Observable<BaseResponseV2<OrderDetailBean>> getOrderDetail(String orderCode) {
        return apiService.getOrderDetail(orderCode);
    }

    @Override
    public Observable<BaseResponseV2> updateNickName(String nickname) {
        return apiService.updateNickName(nickname);
    }

    @Override
    public Observable<BaseResponseV2> updateUserName(String name) {
        return apiService.updateUserName(name);
    }

    @Override
    public Observable<BaseResponseV2> updateBirthday(String birthday) {
        return apiService.updateBirthday(birthday);
    }

    @Override
    public Observable<BaseResponseV2> updateUserPhoto(String headImg) {
        return apiService.updateUserPhoto(headImg);
    }

    @Override
    public Observable<BaseResponse<List<String>>> uploadFiles(MultipartBody.Part file) {
        return apiService.uploadFiles(file);
    }

    @Override
    public Observable<BaseResponse> submitFeedback(String suggestParameter, MultipartBody.Part files) {
        return apiService.submitFeedback(suggestParameter, files);
    }

    @Override
    public Observable<BaseResponse<HomeBean>> getHomeData(int scenicId) {
        return apiService.getHomeData(scenicId);
    }

    @Override
    public Observable<BaseResponseV2<UserInfo>> getUserInfo() {
        return apiService.getUserInfo();
    }

    @Override
    public Observable<BaseResponseV2> getValidateCode(String phoneNumber) {
        return apiService.getValidateCode(phoneNumber);
    }

    @Override
    public Observable<BaseResponseV2<UserInfo>> loginByCode(String phoneNumber, String code, String appId) {
        return apiService.loginByCode(phoneNumber, code, appId);
    }

    @Override
    public Observable<BaseResponseV2<UserInfo>> loginByPassword(String phoneNumber, String password) {
        return apiService.loginByPassword(phoneNumber, password);
    }

    @Override
    public Observable<BaseResponseV2<UserInfo>> register(String phoneNumber, String password, String code) {
        return apiService.register(phoneNumber, password, code);
    }


    @Override
    public Observable<BaseResponseV2<UserInfo>> resetPassword(String phoneNumber, String password, String passwordConfirm, String code) {
        return apiService.resetPassword(phoneNumber, password, passwordConfirm, code);
    }

    @Override
    public Observable<BaseResponse<List<VenueBean>>> getVenues(String scenicId) {
        return apiService.getVenues(scenicId);
    }

    @Override
    public Observable<BaseResponse<VenueDetailBean>> getVenueDetail(int id) {
        return apiService.getVenueDetail(id);
    }

    @Override
    public Observable<BaseResponseV2> logout() {
        return apiService.logout();
    }

    @Override
    public Observable<BaseResponseV2<List<ParkBean>>> getParkList(String offset, String limit) {
        return apiService.getParkList(offset,limit);
    }

    @Override
    public Observable<BaseResponseV2<List<PayHistoryBean>>> getPayHistory(int offset, int limit, int userId) {
        return apiService.getPayHistory(offset, limit, userId);
    }

    @Override
    public Observable<BaseResponseV2<Boolean>> cancelOrder(String orderCode) {
        return apiService.cancelOrder(orderCode);
    }

    @Override
    public Observable<BaseResponseV2<List<CarNumberInfoBean>>> getCarNumberList(int userId) {
        return apiService.getCarNumberList(userId);
    }

    @Override
    public Observable<BaseResponseV2> saveCarNumber(String carNumber, int memberId) {
        return apiService.saveCarNumber(carNumber, memberId);
    }

    @Override
    public Observable<BaseResponseV2> deleteCarNumber(int id) {
        return apiService.deleteCarNumber(id);
    }

    @Override
    public Observable<BaseResponseV2<List<AddressInfoBean>>> getAllAddressInfo(int userId) {
        return apiService.getAllAddressInfo(userId);
    }

    @Override
    public Observable<BaseResponseV2<AddressInfoBean>> getAllAddressInfoDetail(int id) {
        return apiService.getAllAddressInfoDetail(id);
    }

    @Override
    public Observable<BaseResponseV2<List<CityInfoBean>>> getAreaInfo(int id) {
        return apiService.getAreaInfo(id);
    }

    @Override
    public Observable<BaseResponseV2<Boolean>> deleteAddressInfo(int id) {
        return apiService.deleteAddressInfo(id);
    }

    @Override
    public Observable<BaseResponseV2<Boolean>> addAddressInfo(int memberId, String name, String telephone, String area, String detailedAddress, String postCode) {
        return apiService.addAddressInfo(memberId, name, telephone, area, detailedAddress, postCode);
    }

    @Override
    public Observable<BaseResponseV2<Boolean>> updateAddressInfo(int id, int memberId, String name, String telephone, String area, String detailedAddress, String postCode) {
        return apiService.updateAddressInfo(id, memberId, name, telephone, area, detailedAddress, postCode);
    }

    @Override
    public Observable<BaseResponseV2<Boolean>> checkOrderStatus(String orderCode, int type) {
        return apiService.checkOrderStatus(orderCode, type);
    }

    @Override
    public Observable<BaseResponseV2<List<NoPayOrderBean>>> getNoPayInfo(int userId) {
        return apiService.getNoPayInfo(userId);
    }

    @Override
    public Observable<BaseResponseV2<List<PassengerInfoBean>>> getAllTravellerInfo(int userId) {
        return apiService.getAllTravellerInfo(userId);
    }

    @Override
    public Observable<BaseResponseV2<PassengerInfoDetail>> getTravellerInfoDetail(int userId) {
        return apiService.getTravellerInfoDetail(userId);
    }

    @Override
    public Observable<BaseResponseV2<Boolean>> addTravellerInfo(int userId, String name, int sex, String birthday, String idCard) {
        return apiService.addTravellerInfo(userId, name, sex, birthday, idCard);
    }

    @Override
    public Observable<BaseResponseV2<Boolean>> updateTravellerInfo(int id, int userId, String name, int sex, String birthday, String idCard) {
        return apiService.updateTravellerInfo(id, userId, name, sex, birthday, idCard);
    }

    @Override
    public Observable<BaseResponseV2<Boolean>> deleteTravellerInfo(int userId) {
        return apiService.deleteTravellerInfo(userId);
    }

    @Override
    public Observable<BaseResponseV2<ParkPriiceBean>> getParkPirce(String userId, String parkId, String plateNum, String startTime, String endTime) {
        return apiService.getParkPirce(userId, parkId, plateNum, startTime, endTime);
    }

    @Override
    public Observable<BaseResponseV2> checkCarExist(String plateNum) {
        return apiService.checkCarExist(plateNum);
    }

    public Observable<BaseResponseV2<OnLinePayOrderBean>> createOnLinePayOrder(String memberId, String plateNum) {
        return apiService.createOnLinePayOrder(memberId, plateNum);
    }

    @Override
    public Observable<BaseResponseV2<PayInfoBean>> getWechatPayInfo(String orderCode, String type, String payType) {
        return apiService.getWechatPayInfo(orderCode,type,payType);
    }

    @Override
    public Observable<BaseResponseV2> confirmOrderComplete(String orderCode, String type) {
        return apiService.confirmOrderComplete(orderCode,type);
    }

    @Override
    public Observable<BaseResponseV2<ReseverPayOrderBean>> createReseverPayOrder(String memberId, String parkId, String plateNum, String startTime, String endTime) {
        return apiService.createReseverPayOrder(memberId, parkId, plateNum, startTime, endTime);
    }

    @Override
    public Observable<BaseResponseV2<ParkingInfoBean>> getParkingInfo(String plateNum) {
        return apiService.getParkingInfo(plateNum);
    }

}
