package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

final class FragmentManagerState implements Parcelable {
    public static final Creator<FragmentManagerState> CREATOR = new Creator<FragmentManagerState>() {
        public FragmentManagerState createFromParcel(Parcel parcel) {
            return new FragmentManagerState(parcel);
        }

        public FragmentManagerState[] newArray(int i) {
            return new FragmentManagerState[i];
        }
    };
    ArrayList<FragmentState> mActive;
    ArrayList<String> mAdded;
    BackStackState[] mBackStack;
    int mBackStackIndex;
    String mPrimaryNavActiveWho = null;

    public int describeContents() {
        return 0;
    }

    public FragmentManagerState() {
    }

    public FragmentManagerState(Parcel parcel) {
        this.mActive = parcel.createTypedArrayList(FragmentState.CREATOR);
        this.mAdded = parcel.createStringArrayList();
        this.mBackStack = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
        this.mBackStackIndex = parcel.readInt();
        this.mPrimaryNavActiveWho = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.mActive);
        parcel.writeStringList(this.mAdded);
        parcel.writeTypedArray(this.mBackStack, i);
        parcel.writeInt(this.mBackStackIndex);
        parcel.writeString(this.mPrimaryNavActiveWho);
    }
}
