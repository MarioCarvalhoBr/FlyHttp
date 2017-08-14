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
package com.synckware.flyhttp.library.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Classe utilitária para fornecer uma conversão de dados do
 * tipo String em formato JSON em uma lista de objetos de uma classe passada.
 * Created by Mário de Araújo Carvalho on 09/05/2017.
 */
public class ConverterJSONToArray<T> {

    public ConverterJSONToArray() {
    }

    public ArrayList<T> toArrayList(String jsonString, Class<T> clazz) {
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("dd/MM/yy HH:mm:ss");
        Gson gson = builder.create();
        ListParameterizedType type = new ListParameterizedType(clazz);
        ArrayList list = (ArrayList)gson.fromJson(jsonString, type);
        return list;
    }

    public List<T> toList(String jsonString, Class<T> clazz) {
        ArrayList list = this.toArrayList(jsonString, clazz);
        return list;
    }

    private static class ListParameterizedType implements ParameterizedType {
        private Type type;

        private ListParameterizedType(Type type) {
            this.type = type;
        }

        public Type[] getActualTypeArguments() {
            return new Type[]{this.type};
        }

        public Type getRawType() {
            return ArrayList.class;
        }

        public Type getOwnerType() {
            return null;
        }
    }
}
