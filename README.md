# FlyHttp
FlyHttp library for http client requests and served with Android.

É uma biblioteca simples, para fazer requisições web de formar simples e prática.

### 1º - Adicionar as dependências
Ela utiliza o Volley para facilitar o envio de dados para web. Logo, as únicas dependências que você terá de adicionar será a
da biblioteca Volley e GSON do Google:

<b>Gradle</b>

```java
dependencies {
    /*Volley and GSON*/
    compile 'com.android.volley:volley:1.0.0'
    compile 'com.google.code.gson:gson:2.6.2'
}

```
### 2º - Adicionar as permissões pra internet

Lembrando que aplicações que usam conexões com a internet precisam da permissões pra internet no AndroidManifest.xml

<b>AndroidManifest.xml</b>

```xml
dependencies {
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
}
```
Em breve será liberado o link do <strong>GRADLE</strong> para o ```compile```, por hora você terá de baixar o projeto e utilizar as suas classes:

<b>ABAIXO segue uma breve DOCUMENTAÇÃO sobre a utilização da biiblioteca.</b>

 ```java
		//String com a URL desejada para a requisição web.
		String SUA_URL =  "https://api.ipify.org/?format=json";

		//Instância da biblioteca
		FlyHttp flyHttp = new FlyHttp(MainActivity.this);

		//Parâmetros mínimos para uma requição - obrigatórios
		flyHttp.setURL(SUA_URL);
		flyHttp.setMetode(Metode.POST);

		//Requisição com progress na tela - opicional
		flyHttp.setWithProgress(true);

		//Criando um formulário para passar dados pra web - opicional
		FormKeyValue<String, String> params = new FormKeyValue<String, String>();

        //Setando os valores no formulário do tipo chave e valor
        params.put("Key1", "Value1");
        params.put("Key2", "Value2");

		//Passando um formulário na requisição
		flyHttp.setParams(params);

		//Criando a requisição web do tipo String - Obrigatório
        flyHttp.build(new OnCallbackResponseString() {
            @Override
            public void onSuccessString(String result) throws Exception {//Sucesso na requisição - Obrigatório
                Log.i("Result", String.format("Seu endereço de IP é: %s", result);
            }
            @Override
            public void onError(String result) throws Exception {//Erro na requisição - Obrigatório
                Log.e("onError: ", result);
            }
        });
        
		//Criando a requisição web do tipo Json - Obrigatório
        flyHttp.build(new OnCallbackResponseJson() {
            @Override
            public void onSuccessJSONObject(JSONObject result) throws JSONException {
                String text = String.format("Seu endereço de IP é: %s", result.getString("ip");
                Log.i("Result", String.format("Seu endereço de IP é: %s", text);
            }

            @Override
            public void onError(String result) throws Exception {
                Log.e("onError: ", result);
            }
        });
	
	
  ```
# Documentação

<b>
    Para mais detalhes da biblioteca, acesse o <a href="https://github.com/MarioDeAraujoCarvalho/FlyHttp/blob/master/Mini-Cookbook-FlyHttp.pdf" target="_blank">Mini Cookbook</a>, nele você encontrará mais informações sobre o funcionamento da biblioteca.
</b>

# Promova-se
<b>Seu aplicativo usa essa biblioteca? Você pode promovê-lo aqui! Basta enviar o seu pedido que serei feliz em divulgar.</b>
## Aplicativos que usam a biblioteca
* IF-Agenda
* CCN
* GHG-Protocol
* Arbopasto

# Desenvolvido por<br>
Nome: Mário de Araújo Carvalho<br> 
E-mail: mariodearaujocarvalho@gmail.com<br>
Título: FlyHttp
<br>

# Licença
``` 
        Copyright 2017 Mário de Araújo Carvalho
 
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
 
      http://www.apache.org/licenses/LICENSE-2.0
 
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.

````

<a href="https://github.com/MarioDeAraujoCarvalho/FlyHttp/blob/master/LICENSE" target="_blank">Mais detalhes sobre a licença</a>
