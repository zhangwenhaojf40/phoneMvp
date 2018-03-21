package yy.hao.com.testphonemvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import yy.hao.com.testphonemvp.dagger2.ComponentHolder;
import yy.hao.com.testphonemvp.dagger2.compent.ApiCompent;
import yy.hao.com.testphonemvp.dagger2.compent.DaggerApiCompent;
import yy.hao.com.testphonemvp.dagger2.module.ApiModule;
import yy.hao.com.testphonemvp.present.IPresent;

/**
 * Created by Administrator
 * on 2018/3/20 0020.
 */

public abstract  class BaseActivity<T extends IPresent> extends AppCompatActivity implements IBaseView {
    @Inject
    public T mPresent;
    public  ApiCompent mApiCompent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mApiCompent = DaggerApiCompent.builder().appCompent(ComponentHolder.getAppCompent()).apiModule(new ApiModule()).build();
        inject();
        attachView();
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResource());
        ButterKnife.bind(this);
        initView();

        initListenten();
        initData();
    }

    private void attachView() {
        if (mPresent != null) {
            mPresent.attachView(this);
        }
    }
    private void detachView() {
        if (mPresent != null) {
            mPresent.detachView();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        detachView();
    }

    protected abstract void inject();

    protected abstract void initData();

    protected abstract void initListenten();

    protected abstract void initView();

    protected abstract int setLayoutResource();
}
