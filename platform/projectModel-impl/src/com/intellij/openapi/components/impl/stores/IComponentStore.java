// Copyright 2000-2017 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.openapi.components.impl.stores

import com.intellij.configurationStore.StateStorageManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.StateStorage
import com.intellij.openapi.util.Pair
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.util.messages.MessageBus
import org.jetbrains.annotations.SystemIndependent
import org.jetbrains.annotations.TestOnly

interface IComponentStore {
  val storageManager: StateStorageManager

  val stateStorageManager: StateStorageManager

  fun setPath(path: @SystemIndependent String)

  fun initComponent(component: Any, service: Boolean)

  fun initPersistencePlainComponent(component: Any, key: String)

  fun reloadStates(componentNames: Set<String>, messageBus: MessageBus)

  fun reloadState(componentClass: Class<out PersistentStateComponent<*>>)

  fun isReloadPossible(componentNames: Set<String>): Boolean

  class SaveCancelledException : RuntimeException()

  fun save(readonlyFiles: List<Pair<StateStorage.SaveSession, VirtualFile>>)

  @TestOnly
  fun saveApplicationComponent(component: PersistentStateComponent<*>)
}
