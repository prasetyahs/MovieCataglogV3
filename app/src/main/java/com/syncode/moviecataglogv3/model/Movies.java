package com.syncode.moviecataglogv3.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = Movies.TABLE_NAME)
public class Movies implements Parcelable {
    static final String TABLE_NAME = "tb_movie";
    @PrimaryKey()
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    private int voteCount;

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    private String overView;

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    private float voteAverage;

    @ColumnInfo(name = "original_name")
    @SerializedName("original_name")
    private String title;

    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    private String popularity;

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    private String posterPath;

    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    private String language;

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    private String releaseDate;

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    private String backDropPath;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String titleOriginal;

    @ColumnInfo(name = "first_air_date")
    @SerializedName("first_air_date")
    private String dateTv;

    @ColumnInfo(name = "type")
    private String type;

    public Movies(int id, int voteCount, String overView, float voteAverage, String title, String popularity, String posterPath, String language, String releaseDate, String backDropPath, String titleOriginal, String dateTv) {
        this.id = id;
        this.voteCount = voteCount;
        this.overView = overView;
        this.titleOriginal = titleOriginal;
        this.voteAverage = voteAverage;
        this.title = title;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.language = language;
        this.releaseDate = releaseDate;
        this.backDropPath = backDropPath;
        this.dateTv = dateTv;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateTv() {
        return dateTv;
    }

    public String getTitleOriginal() {
        return titleOriginal;
    }

    public int getId() {
        return id;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getBackDropPath() {
        return backDropPath;
    }

    public String getOverView() {
        return overView;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.voteCount);
        dest.writeString(this.overView);
        dest.writeFloat(this.voteAverage);
        dest.writeString(this.title);
        dest.writeString(this.popularity);
        dest.writeString(this.posterPath);
        dest.writeString(this.language);
        dest.writeString(this.releaseDate);
        dest.writeString(this.backDropPath);
        dest.writeString(this.titleOriginal);
        dest.writeString(this.dateTv);
    }

    protected Movies(Parcel in) {
        this.id = in.readInt();
        this.voteCount = in.readInt();
        this.overView = in.readString();
        this.voteAverage = in.readFloat();
        this.title = in.readString();
        this.popularity = in.readString();
        this.posterPath = in.readString();
        this.language = in.readString();
        this.releaseDate = in.readString();
        this.backDropPath = in.readString();
        this.titleOriginal = in.readString();
        this.dateTv = in.readString();
    }

    public static final Parcelable.Creator<Movies> CREATOR = new Parcelable.Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel source) {
            return new Movies(source);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}
