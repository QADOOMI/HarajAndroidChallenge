package text.ocr.mostafa.harajchallenge.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.Locale;

import text.ocr.mostafa.harajchallenge.utils.DateUtils;

public class HarajItem implements Parcelable {

    private String title;
    private String username;
    private String thumbURL;
    private String city;
    private int commentCount;
    private long date;
    private String body;
    private String sinceDate;

    public HarajItem() {
    }

    protected HarajItem(Parcel in) {
        title = in.readString();
        username = in.readString();
        thumbURL = in.readString();
        city = in.readString();
        commentCount = in.readInt();
        date = in.readLong();
        body = in.readString();
        sinceDate = in.readString();
    }

    public static final Creator<HarajItem> CREATOR = new Creator<HarajItem>() {
        @Override
        public HarajItem createFromParcel(Parcel in) {
            return new HarajItem(in);
        }

        @Override
        public HarajItem[] newArray(int size) {
            return new HarajItem[size];
        }
    };

    public String getSinceDate() {
        return sinceDate;
    }

    public void setSinceDate() {
        int minutes = DateUtils.getItemSinceStr(date);
        Log.e(HarajItem.class.getSimpleName(), "setSinceDate, minutes: " + minutes);
        if (minutes < 60) {
            sinceDate = String.format(Locale.US, "Since %d %s", minutes, "minutes");

        } else if (minutes < 1440) {
            sinceDate = String.format(Locale.US, "Since %d %s", minutes / 60, "hours");

        } else if (minutes < 43_200) {
            sinceDate = String.format(Locale.US, "Since %d %s", minutes / 1440, "days");

        } else if (minutes < 518_400) {
            sinceDate = String.format(Locale.US, "Since %d %s", minutes / 43_200, "months");

        } else {
            sinceDate = String.format(Locale.US, "Since %d %s", minutes / 518_400, "years");

        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getThumbURL() {
        return thumbURL;
    }

    public void setThumbURL(String thumbURL) {
        this.thumbURL = thumbURL;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(username);
        dest.writeString(thumbURL);
        dest.writeString(city);
        dest.writeInt(commentCount);
        dest.writeLong(date);
        dest.writeString(body);
        dest.writeString(sinceDate);
    }
}
