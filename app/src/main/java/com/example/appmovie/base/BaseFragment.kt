package com.example.appmovie.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference

abstract class BaseFragment : Fragment() {

    abstract fun getLayoutId(): Int

    private var weakReference: WeakReference<Activity>? = null
    private var alertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weakReference = WeakReference<Activity>(activity)
        alertDialog = getBaseActivity()?.let {
            AlertDialog.Builder(it)
                .setPositiveButton(
                    android.R.string.ok
                ) { dialog, _ -> dialog.cancel() }.create()
        }
        onCreate()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated(view)
        onViewCreated()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.let {
            it.isClickable = true
        }
    }

    private fun getBaseActivity(): Activity? = weakReference?.get()

    open fun onViewCreated() {
        onInit()
        onEvent()
    }

    abstract fun onViewCreated(view: View)

    open fun onInit() {}

    open fun onEvent() {}

    open fun onCreate() {}
}
