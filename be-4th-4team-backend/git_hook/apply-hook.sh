#!/bin/bash
# hooks-install.sh - Git hook 설치 스크립트 (백업 없이 교체)

# Git 루트 디렉토리인지 확인 (.git/hooks 디렉토리 존재 여부)
#if [ ! -d ".git/hooks" ]; then
#    echo "Error: .git/hooks 디렉토리를 찾을 수 없습니다. 이 스크립트를 Git 저장소의 루트에서 실행하세요."
#    exit 1
#fi

# 소스 hook 파일 경로 (git-hooks-set/pre-push)
HOOK_SRC="git_hook/pre-push"
# 대상 hook 파일 경로 (.git/hooks/pre-push)
HOOK_DEST="../.git/hooks/pre-push"



# 소스 파일이 존재하는지 확인
if [ ! -f "$HOOK_SRC" ]; then
    echo "Error: $HOOK_SRC 파일을 찾을 수 없습니다. hook 파일이 올바른 위치에 있는지 확인하세요."
    exit 1
fi


# hook 파일을 복사 후 실행 권한 부여
cp "$HOOK_SRC" "$HOOK_DEST"
chmod +x "$HOOK_DEST"

echo "Pre-push hook이 성공적으로 설치되었습니다."
exit 0