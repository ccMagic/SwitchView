# SwitchView

#### 项目介绍
用于状态切换，支持随意状态个数、自定义Drawable样式。

![image](http://wx2.sinaimg.cn/large/bcc7d265gy1friyth3431g20go0tn7ah.gif)

#### 使用说明
  如下图所示：

  ![image](http://wx3.sinaimg.cn/large/bcc7d265gy1friyxapuo1j20td0gzaff.jpg)

1. 为每种状态新建一个SwitchView.SwitchBean，设置显示的文本即可；
2. 调用  public void setAllStatusList(List<SwitchBean> allStatusList)方法设置状态数据，
此时即会显示出设置的各个状态按钮；
3. 调用   public void setIndex(int index)设置当前显示的哪一项状态，数值表示索引项；
4. 不要忘记设置状态切换监听，status为各个状态显示的文本信息：

       switchView.setOnStatusChangeListener(new SwitchView.OnStatusChangeListener() {
            @Override
            public void statusChange(String status) {
              
            }
        });


