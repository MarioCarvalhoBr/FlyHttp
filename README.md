# FlyHttp
FlyHttp library for http client requests and served with Android.

É uma biblioteca simples, para fazer requisições web de formar simples e prática.

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
Em breve será liberado o link do <strong>GRADLE</strong> para o ```compile```, por hora você terá de baixar o projeto e utilizar as suas classes:

<b>ABAIXO segue uma breve DOCUMENTAÇÃO sobre a utilização da biiblioteca.</b>

 ```java
               /**A URL que deseja fazer a requisição:
               *Nesse caso vamos utilizar esse site, porque ele retorna no formato JSON o IP do cliente.
               **/
                String SUA_URL = "https://api.ipify.org/?format=json";

                /**Instância da classe FlyHttp
                 * Passando 3 parâmetros:
                 * @Método: POST ou GET
                 * @URL: Endereço do site que deseja fazer uma requisição
                 * @Contexto: Contexto para poder fazer a requisição
                 * */

                FlyHttp flyHttp = new FlyHttp(FlyHttp.Metode.GET, SUA_URL, MainActivity.this);

                /**
                 * HashMap com os parâmetros que deseja enviar para o servidor por POST
                 * durante a requisição. No servidor recuperar a chave e o valor
                 */
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("key", "value");

                /**
                 * Setando os parâmetros no objeto que fará a requisição.
                 */
                flyHttp.setParametros(params);

                /**
                 * Fazendo a chamada de requisição do servidor:
                 * @buildJSONObject: Retornará os dados em formato JSON
                 * @buildStringObject: Retornará os dados em formato String
                 *
                 * @Descrição: RequestCallback é uma interface que garantira o callback e o tempo de espera:
                 * Essa interface implementa 3 métodos:
                 * @onSuccessJSONObject: Resultado da requisição em formato JSONObject
                 * @onSuccessString: Resultado da requisição em formato String
                 * @onError: Chamado quando da erro durante a requisição.
                 */
                flyHttp.buildJSONObject(new RequestCallback() {
                    @Override
                    public void onSuccessJSONObject(JSONObject result) throws JSONException {

                        Toast.makeText(getApplicationContext(), "Sucesso na Requisição: Resultado: "+String.format("Seu endereço de IP é: %s", result.getString("ip")),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSuccessString(String result) throws Exception {
                          Toast.makeText(getApplicationContext(), "Sucesso na Requisição: Resultado: Seu Endereçõ de IP é: "+result,Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onError(String result) throws Exception {
                        Toast.makeText(getApplicationContext(), "Erro durante a requisição HTTP: Resultado de Erro: "+result,
                                Toast.LENGTH_LONG).show();
                    }
                });
  ```

<b>Seu aplicativo usa essa biblioteca? Você pode promovê-lo aqui! Basta enviar o seu pedido que serei feliz em divulgar.</b>

#Desenvolvido por<br>
Nome: Mário de Araújo Carvalho<br> 
E-mail: mariodearaujocarvalho@gmail.com<br>
Título: FlyHttp
<br>

#Licença
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
