package io.github.ovso.rollingbanner

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val compositeDisposable = CompositeDisposable()
    private val atomicInt = AtomicInteger(0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val items = listOf(
            R.drawable.bg0,
            R.drawable.bg1,
            R.drawable.bg2
        )
        val adapter = BannerAdapter()
        vp2Main.adapter = adapter
        vp2Main.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                if (state == ViewPager2.SCROLL_STATE_DRAGGING) {
                    Log.d(TAG, "DRAGGING")
                    tvMain.text = "DRAGGING"
                    compositeDisposable.clear()
                } else if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    Log.d(TAG, "IDLE")
                    tvMain.text = "IDLE"
                    var nextPosition = 0
                    when (vp2Main.currentItem) {
                        0 -> nextPosition = 1
                        1 -> nextPosition = 2
                        2 -> nextPosition = 0
                    }
                    compositeDisposable.clear()
                    compositeDisposable += Single.timer(3000, TimeUnit.MILLISECONDS).subscribeBy(
                        onSuccess = {
                            vp2Main.setCurrentItem(nextPosition, true)
                        },
                        onError = {

                        }
                    )
                }
            }
        })

        adapter.submitList(items)
        compositeDisposable += Single.timer(3000, TimeUnit.MILLISECONDS).subscribeBy(
            onSuccess = {
                vp2Main.setCurrentItem(1, true)
            },
            onError = {

            }
        )

/*
        compositeDisposable += Observable.interval(3000, TimeUnit.MILLISECONDS)
            .subscribeBy(
                onNext = {
                    tvMain.text = atomicInt.getAndIncrement().toString()
                },
                onError = {
                    Log.d(TAG, "onError")
                },
                onComplete = {
                    Log.d(TAG, "onComplete")
                }
            )
*/
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}

object SchedulerProvider {
    fun io(): Scheduler = Schedulers.io()
    fun ui(): Scheduler = AndroidSchedulers.mainThread()
}