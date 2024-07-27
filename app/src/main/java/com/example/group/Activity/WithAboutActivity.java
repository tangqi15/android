package com.example.group.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.group.R;

public class WithAboutActivity extends BaseActivity {
TextView tv_about_group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_about);
        initView();
        initSend();
    }


    private void initView() {
        tv_about_group = findViewById(R.id.tv_about_group);
        tv_about_group.setText("NO壹\n" +
                "\n" +
                "大学的社团加入\n" +
                "\n" +
                "or不加入\n" +
                "\n" +
                "\n" +
                "一、对于加入\n" +
                "\n" +
                "\n" +
                "（1）可以在组织里得到锻炼，提高个人的综合能力。\n" +
                "\n" +
                "（2）扩展自己的人脉，结交到除了室友之外的并与自己志同道合的朋友,扩大自己的交际圈。\n" +
                "\n" +
                "（3）学习到一些技能与软件找到或发展自己的兴趣爱好。\n" +
                "\n" +
                "（4）能够为以后步入社会打下基石。社团经历也可以成为你将来简历里的一部分，社团活动中也能学到一些为人处事的方法，提升自身的组织与沟通能力。\n" +
                "二、对于不加入\n" +
                "\n" +
                "\n" +
                "（1）加入一个新的圈子，可能会打破自己当前的舒适状态，也许你要放弃一些东西，而去适应新的圈子。\n" +
                "\n" +
                "（2）你得协调好自己的时间处理工作与学习的关系，防止在社团活动里占用过多的个人时间，而影响学习。\n" +
                "\n" +
                "（3）社团里同样存在矛盾，加入社团无形中给自己增添了负担。\n" +
                "\n" +
                "因此，加入与不加入最终都是看自己的意愿与个人条件，要合理考虑自己的特长和时间，不能盲从。要相信，适合自己的才是最好的。\n" +
                "NO贰\n" +
                "\n" +
                "加入后：你好，接下来的日子里，我们互相多多指教\n" +
                "\n" +
                "\n" +
                "面对新鲜血液的到来，我们在激动与喜悦之际，也会紧张。我们选择在一年之后依然留下，也许有各种各样的原因，但最重要的仍是对这个部门的热爱。正因为这份热爱我们付出的一切努力是为了将我们的事业、我们的部门发扬光大。但说起来容易，做起来难。因此面对你们更多的是有种害怕自己带不好你们的紧张感。\n" +
                "NO叁\n" +
                "\n" +
                "我们都是第一次的角色\n" +
                "\n" +
                "\n" +
                "第一次接触我们时，你也许会想“我的部长很严肃，看起来很凶的样子”，可你不知道的是你的部长还有很多面，严肃只是希望你能严谨对待工作，毕竟你是你们部门精挑细选才留下来的好苗子，是这个部门的希望。所以，面对第一次这样的角色，我们内心里更多的紧张。\n" +
                "\n" +
                "\n" +
                "一年前我们带着自己的憧憬来到了部门，我们也是从什么都不懂开始做起，从一次次的小任务做起。也曾在学业忙碌的时候仍坚持每天跑社团的办公室，哪怕是完成一份小任务；也曾在改了五、六次策划之后依然发着消息向学姐询问：“还有哪里需要修改的吗？”。\n" +
                "\n" +
                "\n" +
                "一路行走，一路为自己打气，给自己鼓励。\n" +
                "NO肆\n" +
                "\n" +
                "写在最后\n" +
                "\n" +
                "\n" +
                "大学里学业仍是根本，社团只是你大学生活的锦上添花。未来路很长，不论在哪，有心学习总是能够有所成长的。愿在社团这里，也能帮助你在大学里找到更好的自己。");
    }
    private void initSend() {
       /* WithAboutGroupModel withAboutGroupModel = new WithAboutGroupModel();
        withAboutGroupModel.getAboutList(listListener);*/
    }

}
