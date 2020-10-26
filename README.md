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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvitesContactViewHolder =

        when (viewType) {
        NORMAL_STATE ->
            NormalViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.invite_earn_row_view, parent, false)
            )
           ADVANCE_STATE -> {
               AdvanceViewHolder(
                   LayoutInflater.from(parent.context)
                       .inflate(R.layout.invite_earn_row_view, parent, false)
               )
          
            }
            else -> {
                ProViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.invite_earn_row_view, parent, false))
      
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

    class InvitesContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

 
       val userNameLabel = itemView.findViewById<AppCompatTextView>(R.id.userNameLabel)
 

         fun bind(name: String) {
            userNameLabel.text=name
         }



    }







