package com.example.appmovie.ui.detail.actor

import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import com.example.appmovie.R
import com.example.appmovie.base.BaseFragment
import com.example.appmovie.data.model.DetailActor
import com.example.appmovie.data.model.External
import java.lang.Exception

class ActorFragment : BaseFragment(), ActorContact.View {

    override fun getLayoutId() = R.layout.fragment_actor

    override fun onViewCreated(view: View) {
        arguments?.getInt(BUNDLE_ID_DETAIL_ACTOR)
    }

    override fun loadContentActorOnSuccess(detailActor: DetailActor) {}

    override fun loadContentExternalOnSuccess(external: External) {}

    override fun onError(exception: Exception?) {
        exception?.let {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val BUNDLE_ID_DETAIL_ACTOR = "BUNDLE_ID_DETAIL_ACTOR"

        fun newInstance(id: Int) = ActorFragment().apply {
            arguments = bundleOf(BUNDLE_ID_DETAIL_ACTOR to id)
        }
    }
}
