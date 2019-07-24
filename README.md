[![](https://jitpack.io/v/jeancsanchez/Via-Cep-API.svg)](https://jitpack.io/#jeancsanchez/Via-Cep-API)

Via CEP API
===================

Uma biblioteca Android para consulta de CEPs. Basicamente ela usa a [API do Via CEP](http://viacep.com.br) :stuck_out_tongue_closed_eyes:


Como utilizar
--------

```kotlin
 ViaCepRequest().buscarCep(60752310,
            onSuccess = { cep: Cep? ->
                // \õ/
            },
            onError = {
                // :(
            })
            
 ViaCepRequest().buscarCepsPorEndereco(
            uf = "CE",
            cidade = "Fortaleza",
            logradouro = "Domingos",
            onSuccess = { ceps ->
                // \õ/
            },
            onError = {
                // :(
            })
```


Download
--------
1- Add the Jitpack Repository in your root build.gradle file:

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
  ```

2- Add the dependency in your project-level build.gradle file:

```groovy
dependencies {
    implementation 'com.github.jeancsanchez:viacepapi:{latest version}'
}
```
