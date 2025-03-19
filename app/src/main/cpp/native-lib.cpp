#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_bsdiffdemo_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"{
    extern int main(int argc, const char *argv[]);
}

extern "C"
JNIEXPORT void JNICALL
ava_com_example_bsdiffdemo_MainViewModle_bsdiffPatch(JNIEnv *env, jobject thiz, jstring old_apk,
                                                      jstring patch, jstring new_apk) {
    const char *old_Apk = env->GetStringUTFChars(old_apk, nullptr);
    const char *patche = env->GetStringUTFChars(patch, nullptr);
    const char *output = env->GetStringUTFChars(new_apk, nullptr);


    const char *argv[] = {"", old_Apk, output, patche};
    main(4,argv);

    env->ReleaseStringUTFChars(old_apk, old_Apk);
    env->ReleaseStringUTFChars(patch, patche);
    env->ReleaseStringUTFChars(new_apk, output);
}
