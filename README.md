# AndroidLearn
日常学习

一.App_ListView 介绍

 1.界面结构,编写布局文件
   1).整体布局-->ListView
   2).item的布局-->相对布局
   
 2.使用ListView+BaseAdapter显示所有应用信息的列表
   
   1).得到所有应用信息数据对象的集合List<AppInfo>
      还需创建一个AppInfo应用封装类(包含创建方法,set()和get()方法)
      
   protected List<AppInfo> getAllAppInfos() {

		List<AppInfo> list = new ArrayList<AppInfo>();
		// 得到应用的packgeManager
		PackageManager packageManager = getPackageManager();
		// 创建一个主界面的intent
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		// 得到包含应用信息的列表
		List<ResolveInfo> ResolveInfos = packageManager.queryIntentActivities(intent, 0);
		// 遍历
		for (ResolveInfo ri : ResolveInfos) {
			// 得到包名
			String packageName = ri.activityInfo.packageName;
			// 得到图标
			Drawable icon = ri.loadIcon(packageManager);
			// 得到应用名称
			String appName = ri.loadLabel(packageManager).toString();
			// 封装应用信息对象
			AppInfo appInfo = new AppInfo(icon, appName, packageName);
			// 添加到list
			list.add(appInfo);
		}
		return list;
	}
  
   2).定义BaseAdapter的实现类:getView()
   
   public View getView(int position, View convertView, ViewGroup parent) {
			// TODO 自动生成的方法存根

			// 如果convertView为null 加载item的布局文件
			if (convertView == null) {
				convertView = View.inflate(MainActivity.this, R.layout.item_main, null);
				//Log.e("TAG", "getView() load layout");
			}
			// 得到 当前行数据对象
			AppInfo appInfo = data.get(position);
			// 得到当前行需要更新的子View对象
			ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_item_icon);
			TextView textView = (TextView) convertView.findViewById(R.id.tv_item_name);
			// 给视图设置数据
			imageView.setImageDrawable(appInfo.getIcon());
			textView.setText(appInfo.getAppName());

			return convertView;
		}
    
    3).初始化成员变量
    
    lv1 = (ListView) findViewById(R.id.lv1);
		data = getAllAppInfos();
		adapter = new AppAdapter();
		lv1.setAdapter(adapter);
    
 3.给ListView设置Item的点击监听,在回调方法中做响应
 
   使用Toast功能显示文件名
   
 4.给ListView设置Item的长按监听,在回调方法中做响应
 
   删除当前行
   更新显示列表的2中方法
   
    lv1.setAdapter(adapter);           显示列表,回到起始位置,重新加载Item视图对象
		adapter.notifyDataSetChanged();    不会回到起始位置,使用所以缓存的Item视图对象
		
二.App_GridView_ChangeName 介绍

1.界面布局
 1).整体布局--> GridView
 
    android:numColumns="3"    显示3×3
 2).Item布局--> LinearLayout
 
2.使用GridView+BaseAdapter显示界面
 1).通过SharedPreferences保存修改的数据
  获取sp的实现方法
  
 sp=getSharedPreferences("cmk", Context.MODE_PRIVATE);
 
 //从sp中读取保存的名称,保证退出后再次进入显示修改后的名称
 
		  if(position==0) {
			 String savedName= sp.getString("NAME", null);
			 if(savedName!=null) {
				 textView.setText(savedName);
			 }
		  }
 
 2).将Adapter和封装类写在一起
 
 public MainAdapter(Context context, String[] names, int[] icons) {
		// TODO 自动生成的构造函数存根
		super();
		this.context=context;
		this.names=names;
		this.icons=icons;
		sp=context.getSharedPreferences("cmk", Context.MODE_PRIVATE);
	}
	 
  3).给GridView设置点击监听
  
  使用Toast功能显示文件名
  
  4).给GridView设置长按监听(只能第一个item有响应)
  
  public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
  
		// TODO 自动生成的方法存根		
		if(position==0) {
			//得到当前显示的名称			
			final TextView textView =(TextView) view.findViewById(R.id.tv_item_name);
			String hint =textView.getText().toString();
			
			//为dialog准备输入对象
			final EditText editText =new EditText(this);
			editText.setHint(hint);
			
			//在回调方法中显示Alertdialog
			new AlertDialog.Builder(this)
			.setTitle("修改名称")
			.setView(editText)
			//处理点击修改
			.setPositiveButton("修改", new DialogInterface.OnClickListener() {				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO 自动生成的方法存根
					String newname =editText.getText().toString();
					//界面更新
					textView.setText(newname);
					//保存到sp中
					sp.edit().putString("NAME", newname).commit();
				}
			})
			.setNegativeButton("取消", new DialogInterface.OnClickListener() {				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO 自动生成的方法存根					
				}
			})
			.show();
		}		
		return true;
	}        

 
