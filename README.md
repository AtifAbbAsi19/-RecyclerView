# -RecyclerView
 The RecyclerView needs an adapter to populate the views in each row with your data.


        <androidx.recyclerview.widget.RecyclerView
                android:id="@+id/taskRecyclerview"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:layout_marginStart="@dimen/_30sdp"
                android:layout_marginTop="@dimen/_6sdp"
                android:layout_marginEnd="@dimen/_8sdp"
                android:layout_marginBottom="@dimen/_8sdp"
                android:orientation="vertical"
                android:overScrollMode="never"
                android:visibility="visible"
                app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
                app:layout_constrainedHeight="true"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintTop_toTopOf="parent"
                tools:itemCount="3"
                tools:listitem="@layout/task_item_view" />
                
                
                @Parcelize
                @SuppressLint("ParcelCreator")
                data class TaskItem(
                @SerializedName("taskName") var taskName: String?,
                ) : Parcelable





