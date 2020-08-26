
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit

class TaskAdapter (var list: ArrayList<TaskItem>) : RecyclerView.Adapter<QualifiedTaskAdapter.TaskViewHolder>() {

    var context: WeakReference<Context?>? = null


    var taskItemsList: ArrayList<TaskItem> = ArrayList()

    var dispose: CompositeDisposable = CompositeDisposable()

    var adapterOnClickListener: AdapterOnClickListener? = null

    init { //init method will call after primary constructor
        this.taskItemsList = ArrayList()
        this.taskItemsList = list

    }

    fun setActivityContext(context: Context) {
        this.context = WeakReference(context)
    }


    fun updateQualifiedTaskItemsList( list: ArrayList<TaskItem>){
        this.taskItemsList = list
        notifyDataSetChanged()
    }

    fun setCustomClickListener(adapterOnClickListener: AdapterOnClickListener){
        this.adapterOnClickListener = adapterOnClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder = //lambda function
            TaskAdapter.QualifiedTaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.asociation_task_item_view, parent, false), context?.get()!!)

    override fun getItemCount(): Int  = taskItemsList.size
            ?: 0 //elvis operator short if else return default size 0

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        if(null!=taskItemsList && taskItemsList.isNotEmpty()) {

            holder.bind(taskItemsList[position])

            dispose.add(
                    RxView.clicks(holder.doNowButton).throttleFirst(1, TimeUnit.SECONDS).subscribe {
                        if (null != commonAdapterOnClickListener) {
                            adapterOnClickListener?.onClick(1, position, taskItemsList[position])
                        }
                    }
            )

        }

    }




    class TaskViewHolder(view: View, context: Context) : RecyclerView.ViewHolder(view) {

        private val taskLabel = view.taskLabel


        fun bind(taskItem: TaskItem?) {

            if(null!=taskItem ){
                taskLabel.text =  taskItem.title
           }else{
                taskLabel.text =  ""
            }

        }


    }

}
