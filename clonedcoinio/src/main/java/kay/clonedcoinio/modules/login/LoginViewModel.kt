package kay.clonedcoinio.modules.login

import com.kay.core.viewmodel.AbsBaseViewModel
import kay.clonedcoinio.models.repositories.LoginRepository
import javax.inject.Inject

/**
 * Created by Kay Tran on 16/3/18.
 * Profile: https://github.com/khatv911
 * Email: khatv911@gmail.com
 */
class LoginViewModel @Inject constructor(private val repo: LoginRepository) : AbsBaseViewModel() {


    fun login(usr: String, pwd: String) {
        setLoading()
        repo.login(usr to pwd)
    }


    init {
        mStateEvent.addSource(repo.requestStateEvent, {
            it?.let { extractState(it) }
        })

        mRetryEvent.addSource(repo.retryEvent, {
            mRetry = it
        })
    }

    fun retry() {
        setLoading()
        mRetry?.invoke()
    }


}