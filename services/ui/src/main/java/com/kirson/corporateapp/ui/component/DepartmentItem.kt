package com.kirson.corporateapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kirson.corporateapp.core.domain.entity.Department
import com.kirson.corporateapp.ui.core.uikit.component.RadioButton
import com.kirson.corporateapp.ui.core.uikit.mappers.toDepartmentType
import com.kirson.corporateapp.ui.core.uikit.theme.AppTheme

@Composable
internal fun DepartmentItem(
    modifier: Modifier = Modifier,
    department: Department.DepartmentType,
    selected: Boolean,
    onDepartmentClick: () -> Unit
){
  Row(
      modifier = modifier
          .fillMaxWidth()
          .heightIn(min = 72.dp)
          .background(color = AppTheme.colors.backgroundPrimary, shape = RoundedCornerShape(16.dp))
          .padding(all = 16.dp),
      verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
        text = department.toDepartmentType(),
        style = AppTheme.typography.body1,
        color = AppTheme.colors.textPrimary
    )
    Spacer(modifier = Modifier.weight(1f))
    RadioButton(
        selected = selected,
        onClick = onDepartmentClick
    )
  }
}