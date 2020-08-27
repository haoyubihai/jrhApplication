package com.jrhlive.library

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import java.io.Serializable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 ***************************************
 * 项目名称:jrhApplication
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/8/18     10:06 AM
 * 用途:
 ***************************************
 */


/***
 *
 *
 * 为了使fragment 传参更简单，使用更方便
 *
 *
 * class vpFragment : Fragment() {
 *       private var param1: String by argument()
 *       private var param2: String by argument()

 *       companion object {
 *        @JvmStatic
 *       fun newInstance(param1: String, param2: Int) =
 *                  vpFragment().apply {
 *                          this.param1 = param1
 *                          this.param2 = param2
 *                      }
 *                  }
 *       }
 *
 *
 *
 */

/**
 * 扩展bundle argments 传值就比较方便了
 *
 * -------------------------before---------------------
 *    arguments = Bundle().apply {
 *      putString(ARG_PARAM1, param1)
 *      putInt(ARG_PARAM2, param2)
 *     }
 *
 * --------------------------after-------------------
 *     arguments = Bundle().apply {
 *     put(ARG_PARAM1,param1)
 *     put(ARG_PARAM2,param2)
 *     }
 *
 */
fun <T> Bundle.put(key: String, value: T) {
    when (value) {
        is Boolean -> putBoolean(key, value)
        is String -> putString(key, value)
        is Int -> putInt(key, value)
        is Short -> putShort(key, value)
        is Long -> putLong(key, value)
        is Byte -> putByte(key, value)
        is ByteArray -> putByteArray(key, value)
        is Char -> putChar(key, value)
        is CharArray -> putCharArray(key, value)
        is CharSequence -> putCharSequence(key, value)
        is Float -> putFloat(key, value)
        is Bundle -> putBundle(key, value)
        is Parcelable -> putParcelable(key, value)
        is Serializable -> putSerializable(key, value)
        else -> throw IllegalStateException("Type of property $key is not supported")
    }
}

class FragmentBundleDataDelegate<T : Any> : ReadWriteProperty<Fragment, T> {
    /**
     * Returns the value of the property for the given object.
     * @param thisRef the object for which the value is requested.
     * @param property the metadata for the property.
     * @return the property value.
     */
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return thisRef.arguments?.get(property.name) as? T ?:  throw IllegalStateException("Property ${property.name} could not be read")
    }

    /**
     * Sets the value of the property for the given object.
     * @param thisRef the object for which the value is requested.
     * @param property the metadata for the property.
     * @param value the value to set.
     */
    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        val args = thisRef.arguments ?: Bundle().also { thisRef::setArguments }
        args.put(property.name, value)
    }
}



fun <T : Any> argument(): ReadWriteProperty<Fragment, T> = FragmentBundleDataDelegate()