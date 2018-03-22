package kay.clonedcoinio.modules.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kay.core.utils.CanSetTitle
import kay.clonedcoinio.R
import timber.log.Timber

/**
 * Created by Kay Tran on 2/2/18.
 * Profile: https://github.com/khatv911
 * Email: khatv911@gmail.com
 */
class MainActivity : AppCompatActivity(), CanSetTitle {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    override fun setTitle(title: String?) {

    }
}
