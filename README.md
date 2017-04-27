# GeneralRecyclerViewFragment for android
[![](https://jitpack.io/v/348476129/GeneralRecyclerViewFragment.svg)](https://jitpack.io/#348476129/GeneralRecyclerViewFragment)

This Fragment can automatically handle the logic of the next page by pulldown and pull-up loading.
Just set up the data source and adapter to start working. Support horizontal and vertical sliding. Supports LinearLayoutManager, StaggeredGridLayoutManager, and GridLayoutManage

这个Fragment能够自动处理下拉刷新和上拉加载下一页的逻辑。
只需要设置数据源和adapter就能开始工作。支持横向和竖向滑动。支持第三方下拉刷新框架。具有高度的定制性

## Download
Step 1. Add the JitPack repository to your build file
```groovy
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }    
		}   
	}
  ```
 Step 2. Add the dependency
 ```groovy
dependencies {
	        compile 'com.github.348476129:GeneralRecyclerViewFragment:1.2'
	}
  ```
## How to use

  Step 1.Create a class that extends to the GeneralPresenter.
  
  第一步.创建一个类，让他继承于GeneralPresenter。
  
  Overrides the refreshData () and loadNextPageData (final int page) methods.

  重写refreshData（）和 loadNextPageData(final int page) 方法。
  
  ```java
 public class TestPresenter extends GeneralPresenter {


    @Override
    public void refreshData() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 50; i++) {
                    list.add("ddd" + i);
                }
                refreshFinish(list); //When the data is acquired
                //    onRefreshError("加载失败");
            }

        }, 2000);
    }

    @Override
    public void loadNextPageData(final int page) {
        Log.d(TAG, page + " ");
        new Handler().postDelayed(new Runnable() {

            public void run() {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 50; i++) {
                    list.add("b" + page + "   " + i);
                }
                loadNextPageFinish(list); //When the data is acquired
                //  onLoadNextError("加载下一页失败");
            }

        }, 2000);
    }
}
```

Step 2.Create a adpter that implements to the GeneralAdapter.

第二步.创建一个adpter，让它实现GeneralAdapter。

```java
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> implements GeneralAdapter {

    private GeneralDataController<String> mStringGeneralDataController;

    public TestAdapter() {
        mStringGeneralDataController = new GeneralDataController<>(this);
    }
    @Override
    public GeneralDataController getGeneralDataController() {
        return mStringGeneralDataController;
    }
	..........your code.........
}
```
Step 3.Create a class that extends to the GeneralRecyclerViewFragment.

第三步. 创建一个类，让他继承GeneralRecyclerViewFragment。

```java
        @NonNull
    @Override
    protected GeneralContract.Presenter getPresenter() {
        if (testPresenter == null) {
            testPresenter = new TestPresenter();
        }
        return testPresenter;
    }

    @NonNull
    @Override
    protected RecyclerView.Adapter getAdapter() {
        if (testAdapter == null) {
            testAdapter = new TestAdapter();
        }
        return testAdapter;
    }

    @NonNull
    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        if (layoutManager == null) {
            layoutManager = new LinearLayoutManager(getContext());
        }
        return layoutManager;
    }
```

See the examples for more details.

## 进阶用法：

如果你想自定义下拉刷新库，或者自己处理一些错误逻辑，请继承BaseGeneraFragment 并实现其中的方法。写法可以参考GeneralRecyclerViewFragment
