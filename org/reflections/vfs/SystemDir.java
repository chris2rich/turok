package org.reflections.vfs;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Lists;
import java.io.File;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class SystemDir implements Vfs.Dir {
   private final File file;

   public SystemDir(File file) {
      if (file == null || file.isDirectory() && file.canRead()) {
         this.file = file;
      } else {
         throw new RuntimeException("cannot use dir " + file);
      }
   }

   public String getPath() {
      return this.file == null ? "/NO-SUCH-DIRECTORY/" : this.file.getPath().replace("\\", "/");
   }

   public Iterable<Vfs.File> getFiles() {
      return (Iterable)(this.file != null && this.file.exists() ? new Iterable<Vfs.File>() {
         public Iterator<Vfs.File> iterator() {
            return new AbstractIterator<Vfs.File>() {
               final Stack<File> stack = new Stack();

               {
                  this.stack.addAll(SystemDir.listFiles(SystemDir.this.file));
               }

               protected Vfs.File computeNext() {
                  while(true) {
                     if (!this.stack.isEmpty()) {
                        File file = (File)this.stack.pop();
                        if (file.isDirectory()) {
                           this.stack.addAll(SystemDir.listFiles(file));
                           continue;
                        }

                        return new SystemFile(SystemDir.this, file);
                     }

                     return (Vfs.File)this.endOfData();
                  }
               }
            };
         }
      } : Collections.emptyList());
   }

   private static List<File> listFiles(File file) {
      File[] files = file.listFiles();
      return files != null ? Lists.newArrayList(files) : Lists.newArrayList();
   }

   public void close() {
   }

   public String toString() {
      return this.getPath();
   }
}
