package com.starter.controllers

import java.util.function.Supplier

/**
  * Created by adam.wells on 23/06/2016.
  */
case class PermissionProcessor[T](permissionCheckResult: Option[T]) {

  def execute(lambda: Supplier[T]): T = {
    permissionCheckResult.getOrElse(lambda.get())
  }

}
