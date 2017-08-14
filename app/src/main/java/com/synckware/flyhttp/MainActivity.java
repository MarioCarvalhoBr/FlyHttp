/*
 * Copyright 2017 Mário de Araújo Carvalho
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 

package com.synckware.flyhttp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.synckware.flyhttp.library.core.FlyHttp;
import com.synckware.flyhttp.library.interfaces.OnCallbackResponseJson;
import com.synckware.flyhttp.library.interfaces.OnCallbackResponseString;
import com.synckware.flyhttp.library.post.FormKeyValue;
import com.synckware.flyhttp.library.utils.Metode;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private TextView mTextoRespostaServidor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mTextoRespostaServidor = (TextView) findViewById(R.id.txt_ip);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

                FlyHttp flyHttp = new FlyHttp(Metode.GET, SUA_URL, MainActivity.this);

                /**
                 * HashMap com os parâmetros que deseja enviar para o servidor por POST
                 * durante a requisição. No servidor recuperar a chave e o valor.
                 */
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("key", "value");

                /**
                 * Setando os parâmetros do POST no objeto que fará a requisição.
                 */
                flyHttp.setParams(params);

                //Requisição com progress na tela - opicional
                flyHttp.setWithProgress(true);

                //Criando a requisição web do tipo String - Obrigatório
                flyHttp.build(new OnCallbackResponseString() {
                    @Override
                    public void onSuccessString(String result) throws Exception {//Sucesso na requisição - Obrigatório
                        Toast.makeText(getApplicationContext(), "Sucesso na Requisição: Resultado: "+String.format("Seu endereço de IP é: %s", result),Toast.LENGTH_LONG).show();
                        mTextoRespostaServidor.setText(String.format("Seu IP é: %s", result));
                        mTextoRespostaServidor.setTextColor(Color.BLUE);

                    }
                    @Override
                    public void onError(String result) throws Exception {//Erro na requisição - Obrigatório
                        Log.e("onError: ", result);
                        Toast.makeText(getApplicationContext(), "Erro durante a requisição HTTP: Resultado de Erro: "+result, Toast.LENGTH_LONG).show();
                        mTextoRespostaServidor.setText("Error: "+result);
                        mTextoRespostaServidor.setTextColor(Color.RED);
                    }
                });

                flyHttp.build(new OnCallbackResponseJson() {
                    @Override
                    public void onSuccessJSONObject(JSONObject result) throws JSONException {
                        Toast.makeText(getApplicationContext(), "Sucesso na Requisição: Resultado: "+String.format("Seu endereço de IP é: %s", result.getString("ip")),Toast.LENGTH_LONG).show();
                        mTextoRespostaServidor.setText(String.format("Seu IP é: %s", result.getString("ip")));
                        mTextoRespostaServidor.setTextColor(Color.BLUE);
                    }

                    @Override
                    public void onError(String result) throws Exception {
                        Toast.makeText(getApplicationContext(), "Erro durante a requisição HTTP: Resultado de Erro: "+result,
                                Toast.LENGTH_LONG).show();

                        mTextoRespostaServidor.setText("Error: "+result);
                        mTextoRespostaServidor.setTextColor(Color.RED);
                    }
                });

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.requisicao_simples) {

            //Exemplo de uma requisição simples
            FlyHttp http = new FlyHttp(Metode.GET, "https://api.ipify.org/?format=json",MainActivity.this);
            http.build(new OnCallbackResponseString() {
                @Override
                public void onSuccessString(String result) throws Exception {
                    Toast.makeText(MainActivity.this, "Resposta: "+result, Toast.LENGTH_SHORT).show();
                    mTextoRespostaServidor.setText(String.format("Seu IP é: %s", result));
                    mTextoRespostaServidor.setTextColor(Color.BLUE);
                }
                @Override
                public void onError(String result) throws Exception {
                    Toast.makeText(MainActivity.this, "Erro: "+result, Toast.LENGTH_SHORT).show();
                }
            });

        }else if(id == R.id.requisicao_get){
            //Exemplo de uma requisição simples
            String URL_GET = "https://api.ipify.org/?format=json";
            FlyHttp http = new FlyHttp(Metode.GET, URL_GET, MainActivity.this);

            http.build(new OnCallbackResponseString() {
                @Override
                public void onSuccessString(String result) throws Exception {
                    Toast.makeText(MainActivity.this, "Resposta: "+result, Toast.LENGTH_SHORT).show();
                    mTextoRespostaServidor.setText(String.format("Seu IP é: %s", result));
                    mTextoRespostaServidor.setTextColor(Color.BLUE);
                }
                @Override
                public void onError(String result) throws Exception {
                    Toast.makeText(MainActivity.this, "Erro: "+result, Toast.LENGTH_SHORT).show();
                }
            });

        }else if(id == R.id.requisicao_post){

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
            params.put("Key", "Value");
           
            //Passando um formulário na requisição
            flyHttp.setParams(params);

            //Criando a requisição web do tipo String - Obrigatório
            flyHttp.build(new OnCallbackResponseString() {
                @Override
                public void onSuccessString(String result) throws Exception {//Sucesso na requisição - Obrigatório
                    Toast.makeText(MainActivity.this, "Resposta: "+result, Toast.LENGTH_SHORT).show();
                    mTextoRespostaServidor.setText(String.format("Seu IP é: %s", result));
                    mTextoRespostaServidor.setTextColor(Color.BLUE);

                }
                @Override
                public void onError(String result) throws Exception {//Erro na requisição - Obrigatório
                    Log.e("onError: ", result);
                }
            });

        }else if(id == R.id.requisicao_with_progress){
            //Exemplo de uma requisição simples com progress

            //O progress pode ser ativado via construtor (4º paramâmetro)
            FlyHttp http = new FlyHttp(Metode.GET, "https://api.ipify.org/?format=json", MainActivity.this, true);
            //Ou Pode ser ativado via método set
            //http.setWithProgress(true);

            http.build(new OnCallbackResponseString() {
                @Override
                public void onSuccessString(String result) throws Exception {
                    Toast.makeText(MainActivity.this, "Resposta: "+result, Toast.LENGTH_SHORT).show();
                    mTextoRespostaServidor.setText(String.format("Seu IP é: %s", result));
                    mTextoRespostaServidor.setTextColor(Color.BLUE);
                }
                @Override
                public void onError(String result) throws Exception {
                    Toast.makeText(MainActivity.this, "Erro: "+result, Toast.LENGTH_SHORT).show();
                }
            });

        }

        return super.onOptionsItemSelected(item);
    }
}
