All changes must be applied to app/build.gradle file.

1) Add this line to the beginning of the file (after the "apply plugin: 'com.android.application'" line).
    apply plugin: 'com.neenbedankt.android-apt'

2) Change targetSdkVersion to 24.

3) Clear the dependencies block and paste this into it.
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:25.2.0'
    compile 'com.squareup.retrofit2:retrofit:2.0.2'
    compile 'com.squareup.retrofit2:converter-gson:2.0.2'
    compile 'com.jakewharton:butterknife:8.0.1'
    apt 'com.jakewharton:butterknife-compiler:8.0.1'
    compile group: 'javax.annotation', name: 'jsr250-api', version: '1.0'
    compile 'com.android.support:recyclerview-v7:25.2.0'
    compile 'com.koushikdutta.ion:ion:2.+'
    
