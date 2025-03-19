// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("bsdifflibrary");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("bsdifflibrary")
//      }
//    }


#include <jni.h>

extern "C"{
    extern int main(int argc, const char *argv[]);
}
extern "C"
JNIEXPORT void JNICALL
Java_com_example_bsdifflibrary_bsdiffUtil_bsdiffPatch(JNIEnv *env, jobject thiz, jstring old_apk,
jstring patch, jstring new_apk) {
    const char *old_Apk = env->GetStringUTFChars(old_apk, nullptr);
    const char *patche = env->GetStringUTFChars(patch, nullptr);
    const char *output = env->GetStringUTFChars(new_apk, nullptr);


    const char *argv[] = {"", old_Apk, output, patche};
    main(4, argv);

    env->ReleaseStringUTFChars(old_apk, old_Apk);
    env->ReleaseStringUTFChars(patch, patche);
    env->ReleaseStringUTFChars(new_apk, output);
}