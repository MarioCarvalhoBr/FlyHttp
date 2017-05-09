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
Em breve será liberado o link do <strong>GRADLE</strong>para o ```java compile```, por hora você terá de baixar o projeto e utilizar as suas classes:

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
                 * @buildStringbject: Retornará os dados em formato String
                 *
                 * @Descrição: VolleyCallback é uma interface que garantira o callback e o tempo de espera:
                 * Essa interface implementa 3 métodos:
                 * @onSuccessJSONObject: Resultado da requisição em formato JSONObject
                 * @onSuccessString: Resultado da requisição em formato String
                 * @onError: Chamado quando da erro durante a requisição.
                 */
                flyHttp.buildJSONObject(new VolleyCallback() {
                    @Override
                    public void onSuccessJSONObject(JSONObject result) throws JSONException {

                        Toast.makeText(getApplicationContext(), "Sucess!"+String.format("Your IP is: %s", result.getString("ip")),
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSuccessString(String result) throws Exception {
                            Toast.makeText(getApplicationContext(), "Sucess! Your IP is: "+result),
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(String result) throws Exception {
                        Toast.makeText(getApplicationContext(), "Error",
                                Toast.LENGTH_LONG).show();
                    }
                });
  ```

<b>Seu aplicativo usa essa biblioteca? Você pode promovê-lo aqui! Basta enviar o seu pedido que será feliz em divulgar.</b>

#Desenvolvido por<br>
Nome: Mário de Araújo Carvalho<br> 
E-mail: mariodearaujocarvalho@gmail.com<br>
Título: FlyHttp
<br>

#Licença
``` 
    FlyHttp Copyright (C) 2017   Mário de Araújo Carvalho
This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
This is free software, and you are welcome to redistribute it
under certain conditions; type `show c' for details.

    The hypothetical commands `show w' and `show c' should show the appropriate
parts of the General Public License.  Of course, your program's commands
might be different; for a GUI interface, you would use an "about box".

    You should also get your employer (if you work as a programmer) or school,
if any, to sign a "copyright disclaimer" for the program, if necessary.
For more information on this, and how to apply and follow the GNU GPL, see
<http://www.gnu.org/licenses/>.

    The GNU General Public License does not permit incorporating your program
into proprietary programs.  If your program is a subroutine library, you
may consider it more useful to permit linking proprietary applications with
the library.  If this is what you want to do, use the GNU Lesser General
Public License instead of this License.  But first, please read
<http://www.gnu.org/philosophy/why-not-lgpl.html>.
 
````
