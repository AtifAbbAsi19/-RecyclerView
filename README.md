# -RecyclerView
 The RecyclerView needs an adapter to populate the views in each row with your data.

### -Simple Verical RecyclerView UI

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
                
 ### -Simple GRID RecyclerView UI   

                 <androidx.recyclerview.widget.RecyclerView
                    android:id="@+id/gridView"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_marginStart="16dp"
                    android:layout_marginTop="14dp"
                    android:orientation="vertical"
                    android:overScrollMode="never"
                    android:nestedScrollingEnabled="false"
                    android:visibility="visible"
                    android:layout_marginBottom="16dp"
                    app:layoutManager="androidx.recyclerview.widget.GridLayoutManager"
                    app:layout_constrainedHeight="true"
                    app:layout_constraintEnd_toEndOf="parent"
                    app:layout_constraintHorizontal_bias="1.0"
                    app:layout_constraintStart_toStartOf="parent"
                    app:layout_constraintTop_toBottomOf="@+id/category_tabs"
                    app:layout_constraintVertical_bias="0.0"
                    app:spanCount="2"
                    tools:itemCount="4"
                    tools:listitem="@layout/product_item_view" />
                    
                    
   ### -Simple TAB UI              
                    
                     <com.google.android.material.tabs.TabLayout
                    android:id="@+id/category_tabs"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginTop="12dp"
                    android:background="@drawable/tabs_background"
                    app:layout_constraintEnd_toEndOf="parent"
                    app:layout_constraintStart_toStartOf="parent"
                    app:layout_constraintTop_toBottomOf="@+id/green_divider"
                    app:tabBackground="@drawable/tab_background_selector"
                    app:tabGravity="center"
                    app:tabIndicatorHeight="0dp"
                    app:tabMode="scrollable"

                    app:tabRippleColor="@null"
                    app:tabTextAppearance="@android:style/TextAppearance.Widget.TabWidget"
                   >

                    <com.google.android.material.tabs.TabItem
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_marginStart="8dp"
                        android:text="All" />
                    <com.google.android.material.tabs.TabItem
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_marginStart="8dp"
                        android:text="New" />
           

                </com.google.android.material.tabs.TabLayout>
                
 ### -MODEL CLASS (POJO/OBJECT)
                
               
                @Parcelize
                @SuppressLint("ParcelCreator")
                data class TaskItem(
                @SerializedName("taskName") var taskName: String?,
                @SerializedName("title") var title: String?
                ) : Parcelable
                
                
           
 ### -Callback, interface For action Items
 
                   interface AdapterOnClickListener {
                       fun onClick(id: Int, position: Int, vararg args: Any?)
                 }


 ### -Adapter For Multiple Views


        private const val NORMAL_STATE = 212
        private const val ADVANCE_STATE = 313
        private const val PRO_STATE = 414

    class CustomAdapter(arryList: ArrayList<Any>, context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var arryList: MutableList<Any> = arrayListOf()
    var context: WeakReference<Context>? = null

    lateinit var adapterOnClickListener: CustomAdapter.AdapterOnClickListener

    init {
        this.arryList = arryList
        this.context = WeakReference(context)
    }


    fun updateAdapterOnClickListener(adapterOnClickListener: CustomAdapter.AdapterOnClickListener) {
        this.adapterOnClickListener = adapterOnClickListener
    }

    fun updateList(list: MutableList<Any>) {
        this.arryList = list
        notifyDataSetChanged()
    }

    fun updateSingleItem(position : Int,'object': object) {
        this.arryList.set(arryList.indexOf('object'),'object')
        notifyItemChanged(arryList.indexOf('object'))
    }

    override fun getItemViewType(position: Int): Int =
        when {
            arryList[position] is String -> {
                NORMAL_STATE
            }
            arryList[position] is Float -> {
                ADVANCE_STATE
            }
            else -> {
                PRO_STATE
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =

        when (viewType) {
        NORMAL_STATE ->{
            NormalViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.normal_view_item, parent, false)
            )
           /*   
              val binding: NormalViewItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.normal_view_item, parent,false)

        return NormalViewHolder(binding)

          val infalter = LayoutInflater.from(parent.context)
            return NormalViewItemBinding(
                NormalViewHolder.inflate(infalter)
            )*/
            
            }
           ADVANCE_STATE -> {
               AdvanceViewHolder(
                   LayoutInflater.from(parent.context)
                       .inflate(R.layout.advance_view_item, parent, false)
               )
          
            }
            else -> {
                ProViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.pro_view_item, parent, false))
      
            }
        }




    //get the size of array
    override fun getItemCount(): Int = invitesContactList?.size ?: 0
    ?: 0 //elvis operator short if else return default size 0
    //    override fun getItemCount(): Int = Int.MAX_VALUE

    //binding the screen with view
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =

        when {
            arryList[position] is UserObject -> {
                (holder as NormalViewHolder).bind(arryList[position] as String)
            }
            arryList[position] is NewUser -> {
                (holder as AdvanceViewHolder).bind(arryList[position] as Float)
            
            }
            else ->{
                (holder as ProViewHolder).bind(arryList[position] as Double)
            }
        }


     
    }


    interface AdapterOnClickListener {
        fun onClick(id: Any, position: Int, vararg args: Any?)
    }

    class NormalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

 
       val userNameLabel = itemView.findViewById<AppCompatTextView>(R.id.userNameLabel)
 

         fun bind(name: String) {
            userNameLabel.text=name
         }



    }







