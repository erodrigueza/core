/*
 * JBoss, Home of Professional Open Source
 * Copyright 2008, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.weld.bootstrap.events;

import java.lang.reflect.Type;

import javax.enterprise.inject.spi.AnnotatedMethod;
import javax.enterprise.inject.spi.AnnotatedParameter;
import javax.enterprise.inject.spi.ProcessProducerMethod;

import org.jboss.weld.bean.ProducerMethod;
import org.jboss.weld.manager.BeanManagerImpl;

public class ProcessProducerMethodImpl<T, X> extends AbstractProcessProducerBean<T, X, ProducerMethod<T, X>> implements ProcessProducerMethod<T, X>
{
   
   
   public static <T, X> void fire(BeanManagerImpl beanManager, ProducerMethod<T, X> bean)
   {
      if (beanManager.isBeanEnabled(bean))
      {
         new ProcessProducerMethodImpl<T, X>(beanManager, bean) {}.fire();
      }
   }

   public ProcessProducerMethodImpl(BeanManagerImpl beanManager, ProducerMethod<T, X> bean)
   {
      super(beanManager, ProcessProducerMethod.class, new Type[] { bean.getWeldAnnotated().getDeclaringType().getBaseType(), bean.getWeldAnnotated().getBaseType() }, bean);
   }

   @SuppressWarnings("unchecked")
   public AnnotatedParameter<T> getAnnotatedDisposedParameter()
   {
      if (getBean().getDisposalMethod() != null)
      {
         return (AnnotatedParameter<T>) getBean().getDisposalMethod().getDisposesParameter();
      }
      else
      {
         return null;
      }
   }

   @SuppressWarnings("unchecked")
   public AnnotatedMethod<T> getAnnotatedProducerMethod()
   {
      if (getBean().getWeldAnnotated() != null)
      {
         return (AnnotatedMethod<T>) getBean().getWeldAnnotated();
      }
      else
      {
         return null;
      }
   }

   

}
