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

public class AdEntity implements Parcelable {
    /**
     * VersionNum : 1
     * Advertisements : [{"AdUrl":"http://www.us2080.com","AdImage":"0570d408-ddd7-c913-0786-9d113cb0a64c","AdTitle":"快乐的学习","AdDesc":"111222","CreateTime":"03 20 2017  7:02PM","Sort":1,"Id":"806fd408-83a2-8d6a-6fcf-613ccc534723"},{"AdUrl":"234","AdImage":"fc6fd408-ec84-57f1-0786-9d113cb0a46d","AdTitle":"123","AdDesc":"111","CreateTime":"03 21 2017  9:49AM","Sort":1,"Id":"fc6fd408-db84-69ce-0786-9d113cb0a46c"}]
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
         * AdUrl : http://www.us2080.com
         * AdImage : 0570d408-ddd7-c913-0786-9d113cb0a64c
         * AdTitle : 快乐的学习
         * AdDesc : 111222
         * CreateTime : 03 20 2017  7:02PM
         * Sort : 1
         * Id : 806fd408-83a2-8d6a-6fcf-613ccc534723
         */

        private String AdUrl;
        private String AdImage;
        private String AdTitle;
        private String AdDesc;
        private String CreateTime;
        private int Sort;
        private String Id;

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

    public AdEntity() {
    }

    protected AdEntity(Parcel in) {
        this.VersionNum = in.readInt();
        this.Advertisements = new ArrayList<AdvertisementsEntity>();
        in.readList(this.Advertisements, AdvertisementsEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<AdEntity> CREATOR = new Parcelable.Creator<AdEntity>() {
        @Override
        public AdEntity createFromParcel(Parcel source) {
            return new AdEntity(source);
        }

        @Override
        public AdEntity[] newArray(int size) {
            return new AdEntity[size];
        }
    };
}
