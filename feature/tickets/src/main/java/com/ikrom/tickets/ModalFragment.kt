package com.ikrom.tickets

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ui.adapters.extensions.layoutConfigure
import com.example.ui.adapters.extensions.setBackgroundTint
import com.example.ui.adapters.extensions.setFullHeight
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modal, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        layoutConfigure(dialog) {bottomSheet ->
            bottomSheet?.let {
                bottomSheet.setFullHeight()
                bottomSheet.setBackgroundTint(requireContext(), com.example.ui.R.color.bottom_sheet_color)

            }
        }
        return dialog
    }

    companion object {
        const val TAG = "Modal fragment"
    }
}