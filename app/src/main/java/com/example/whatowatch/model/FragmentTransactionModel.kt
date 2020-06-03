package com.example.whatowatch.model

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.whatowatch.R
import java.util.ArrayList

class FragmentTransactionModel(_fragmentInstance: Fragment, _args: Bundle?) {
    var fragmentInstance: Fragment = _fragmentInstance
    var args: Bundle? = _args
    var lAnimations: List<Int> = ArrayList()
    var iContainer = R.id.vBaseContent
    var registerBackStackTransaction = false
}