package com.bolooo.artlesson.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-11-17
 * DES : ${}
 * =======================================
 */

public class BannerEntity implements Parcelable {

    /**
     * VersionNum : 1
     * Advertisements : [{"AdType":1,"AdUrl":"sample string 1","AdImage":"sample string 2","AdTitle":"sample string 3","AdDesc":"sample string 4","CreateTime":"sample string 5","Sort":6,"Id":"sample string 7"},{"AdType":1,"AdUrl":"sample string 1","AdImage":"sample string 2","AdTitle":"sample string 3","AdDesc":"sample string 4","CreateTime":"sample string 5","Sort":6,"Id":"sample string 7"}]
     */

    private int VersionNum;
    private List<AdvertisementsEntity> Advertisements;

    public int getVersionNum() {
        return VersionNum;
    }

    public void setVersionNum(int VersionNum) {
        this.VersionNum = VersionNum;
    }

    public List<AdvertisementsEntity> getAdvertisements() {
        return Advertisements;
    }

    public void setAdvertisements(List<AdvertisementsEntity> Advertisements) {
        this.Advertisements = Advertisements;
    }

    public static class AdvertisementsEntity {
        /**
         * AdType : 1
         * AdUrl : sample string 1
         * AdImage : sample string 2
         * AdTitle : sample string 3
         * AdDesc : sample string 4
         * CreateTime : sample string 5
         * Sort : 6
         * Id : sample string 7
         */

        private int AdType;
        private String AdUrl;
        private String AdImage;
        private String AdTitle;
        private String AdDesc;
        private String CreateTime;
        private int Sort;
        private String Id;

        public int getAdType() {
            return AdType;
        }

        public void setAdType(int AdType) {
            this.AdType = AdType;
        }

        public String getAdUrl() {
            return AdUrl;
        }

        public void setAdUrl(String AdUrl) {
            this.AdUrl = AdUrl;
        }

        public String getAdImage() {
            return AdImage;
        }

        public void setAdImage(String AdImage) {
            this.AdImage = AdImage;
        }

        public String getAdTitle() {
            return AdTitle;
        }

        public void setAdTitle(String AdTitle) {
            this.AdTitle = AdTitle;
        }

        public String getAdDesc() {
            return AdDesc;
        }

        public void setAdDesc(String AdDesc) {
            this.AdDesc = AdDesc;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public int getSort() {
            return Sort;
        }

        public void setSort(int Sort) {
            this.Sort = Sort;
        }

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.VersionNum);
        dest.writeList(this.Advertisements);
    }

    public BannerEntity() {
    }

    protected BannerEntity(Parcel in) {
        this.VersionNum = in.readInt();
        this.Advertisements = new ArrayList<AdvertisementsEntity>();
        in.readList(this.Advertisements, AdvertisementsEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<BannerEntity> CREATOR = new Parcelable.Creator<BannerEntity>() {
        @Override
        public BannerEntity createFromParcel(Parcel source) {
            return new BannerEntity(source);
        }

        @Override
        public BannerEntity[] newArray(int size) {
            return new BannerEntity[size];
        }
    };
}
