### Structure
#### Fix
- Used when the commit intends/represents a fix a bug
- Example of multiparagraph fix commit
```
fix: prevent racing of requests

Introduce a request id and a reference to latest request. Dismiss
incoming responses other than from latest request.

Remove timeouts which were used to mitigate the racing issue but are
obsolete now.

Reviewed-by: Z
Refs: #123
```

#### Feat
-  A commit of the _type_ `feat` introduces a new feature to the codebase (this correlates with [`MINOR`](http://semver.org/#summary) in Semantic Versioning). 
- Example of a feat commit
```
feat!: send an email to the customer when a product is shipped
```

#### BREAKING CHANGE
- A commit that has a footer `BREAKING CHANGE:`, or appends a `!` after the type/scope, introduces a breaking API change (correlating with [`MAJOR`](http://semver.org/#summary) in Semantic Versioning). A BREAKING CHANGE can be part of commits of any _type_.
- Example of a BREAKING CHANGE commit
```
feat: allow provided config object to extend other configs

BREAKING CHANGE: `extends` key in config file is now used for extending other config files
```

#### Other
- _types_ other than `fix:` and `feat:` are allowed, for example [@commitlint/config-conventional](https://github.com/conventional-changelog/commitlint/tree/master/%40commitlint/config-conventional) (based on the [Angular convention](https://github.com/angular/angular/blob/22b96b9/CONTRIBUTING.md#-commit-message-guidelines)) recommends `build:`, `chore:`, `ci:`, `docs:`, `style:`, `refactor:`, `perf:`, `test:`, and others.
- Example (commit message without body):
```
docs: correct spelling of CHANGELOG
```

#### Footers
- _footers_ other than `BREAKING CHANGE: <description>` may be provided and follow a convention similar to [git trailer format](https://git-scm.com/docs/git-interpret-trailers).